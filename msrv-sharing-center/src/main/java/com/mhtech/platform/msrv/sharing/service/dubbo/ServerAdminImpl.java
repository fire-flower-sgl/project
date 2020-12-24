package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.pojo.MonitorServer.PrefixCode;
import com.mhtech.platform.msrv.pojo.MonitorServer.ServerType;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ServerStatus;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerAlertParamVO;
import com.mhtech.platform.msrv.pojo.ServerBriefVO;
import com.mhtech.platform.msrv.pojo.ServerGrpInfo;
import com.mhtech.platform.msrv.pojo.ServerInfo;
import com.mhtech.platform.msrv.pojo.ServerPageInfo;
import com.mhtech.platform.msrv.sharing.dao.mapper.ParamAlertMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ParamAlertTypeMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ParamAlert;
import com.mhtech.platform.msrv.sharing.dao.model.ParamAlertType;
import com.mhtech.platform.msrv.sharing.dao.model.ServerAdmin;
import com.mhtech.platform.msrv.sharing.utils.GeneralConvertor;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

import freemarker.core.ReturnInstruction.Return;

/**
 * @see IServerAdminService
 * @author Guo
 *
 */
@Service("serverAdminService")
public class ServerAdminImpl implements IServerAdminService {

	private final Logger logger = LoggerFactory
			.getLogger(ServerAdminImpl.class);

	@Autowired
	private IworkService workService;

	@Autowired
	private ServerAdminMapper serverAdminMapper;

	@Autowired
	private ParamAlertTypeMapper paramAlertTypeMapper;

	@Autowired
	private ParamAlertMapper paramAlertMapper;

	@Autowired
	private RedisUtils redisUtils;

	@Value("${monitor-server-code}")
	private String monitorServerCode;

	@Value("${monitor-subserver-code}")
	private String monitorSubserverCode;

	private final int increment = 1;

	@Autowired
    private GeneralConvertor dozer ;

	@Autowired
	ISysParameterService iSysParameterService;
	
	@Transactional
	@Override
	public ServerBriefVO addServerInfo(ServerInfo server) {
		ServerBriefVO sbvo = new ServerBriefVO();
		try {
			ServerAdmin svrAdmin = buildIfNotDuplicateServer(server);
			sbvo.setServerCode(svrAdmin.getServerCode());
			sbvo.setServerId(svrAdmin.getId().toString());
			List<ServerAlertParamVO> params = server.getParams();
			if (!CollectionUtils.isEmpty(params)) {
				for (ServerAlertParamVO serverAlertParamVO : params) {
					ParamAlertType paramAlertType = paramAlertTypeMapper
							.getParamAlertType(serverAlertParamVO
									.getAlertCode());
					if (Objects.isNull(paramAlertType)) {
						throw new RuntimeException("预警类型不存在");
					}
					ParamAlert paramAlert = new ParamAlert(
							workService.getNextId(), svrAdmin.getId(),
							serverAlertParamVO.getParamName(),
							serverAlertParamVO.getParamAlias(),
							serverAlertParamVO.getAlertValue(),
							paramAlertType.getTypeCode(),
							paramAlertType.getTypeAlias(),
							ParamAlertStatus.Activator.getCode(), new Date());
					paramAlertMapper.insertSelective(paramAlert);
				}
			}
			serverAdminMapper.insert(svrAdmin);
		} catch (Exception e) {
			logger.error("新增服务失败", e);
			throw new RuntimeException(e.getMessage());
		}
		return sbvo;
	}

	/**
	 * 若不存在服务重复则创建对象
	 * 
	 * @param server
	 * @return
	 */
	private ServerAdmin buildIfNotDuplicateServer(ServerInfo server) {
		ServerAdmin parent = getParentServer(server.getParentServer());
		ServerAdmin condition = new ServerAdmin();
		condition.setServerName(server.getServerName());
		List<ServerAdmin> servers = null;
		if (parent != null) {// 同一个组内，不能出现子服务名相同
			condition.setParentServer(server.getParentServer());
		} else {// 同一IP不能出现相同服务名
			condition.setIp(server.getIp());
		}
		servers = serverAdminMapper.selectEntities(condition);
		if (!CollectionUtils.isEmpty(servers)) {
			throw new RuntimeException("该服务器中已存在同服务名");
		}
		return buildServerAdmin(server, parent);
	}

	private ServerAdmin getParentServer(String parentServer) {
		if (parentServer == null)
			return null;
		ServerAdmin condition = new ServerAdmin();
		condition.setParentServer(parentServer);
		List<ServerAdmin> servers = serverAdminMapper.selectEntities(condition);
		if (CollectionUtils.isEmpty(servers)) {
			throw new RuntimeException("服务器组ID错误");
		}
		return servers.get(0);
	}

	private ServerAdmin buildServerAdmin(ServerInfo server, ServerAdmin parent) {
		ServerAdmin sa = new ServerAdmin();
		sa.setId(workService.getNextId());
		sa.setEmail(server.getEmail());
		sa.setIp(server.getIp());
		sa.setMobile(server.getMobile());
		sa.setParentServer(server.getParentServer());
		sa.setServerName(server.getServerName());
		sa.setUsername(server.getServerName());
		sa.setServerType(server.getServerType());
		sa.setApplyProjects(StringUtils.isEmpty(server.getApplyProjects()) ? "" : server.getApplyProjects().toString());
		setServerCode(sa, parent);
		//sa.setStatus(ServerStatus.Disabled.getCode());
		//设置状态为完成-执行结束
		sa.setStatus("2");
		sa.setCreatedTime(new Date());
		return sa;
	}

	/**
	 * 设置服务编码
	 * 
	 * @param server
	 * @param parent
	 */
	private void setServerCode(ServerAdmin server, ServerAdmin parent) {
		ServerType serverType = ServerType.valueOf(server.getServerType());
		String key = parent == null ? monitorServerCode : monitorSubserverCode;
		String prefix = "";
		long curtVal = 0;
		switch (serverType) {
		case HARDWARE:
			prefix = PrefixCode.FW.getPrefix();
			break;
		case SOFTWARE:
			prefix = PrefixCode.YY.getPrefix();
			break;
		}
		curtVal = redisUtils.incr(key, increment);
		if (StringUtils.isEmpty(prefix))
			throw new RuntimeException("服务类型错误");
		server.setServerCode(String.format("%s%04d", prefix, curtVal));
	}

	@Override
	public void addServerGrpInfo(ServerGrpInfo grpInfo) {
		List<ServerAdmin> servers = new ArrayList<ServerAdmin>();
		ServerAdmin svrAdmin = buildServerAdmin(grpInfo, null);
		servers.add(svrAdmin);
		if (!CollectionUtils.isEmpty(grpInfo.getSubServers())) {
			grpInfo.getSubServers().forEach(srv -> {
				servers.add(buildServerAdmin(srv, svrAdmin));
			});
		}
		serverAdminMapper.batchInsert(servers);
	}

	@Override
	public int updateServerInfo(ServerInfo server) {
		//ServerAdmin mt = dozer.convertor(server, ServerAdmin.class);
		return serverAdminMapper.updateByPrimaryKey(server);
	}

	@Override
	public ServerAdminInfo getServerInfo(long id) {
		return serverAdminMapper.findServerDetail(id);
	}

	@Override
	public Page<ServerAdminInfo> listPage(ServerPageInfo spi) {
		PageHelper.startPage(spi.getPageNo(), spi.getPageSize(),spi.getOrderBy());
		com.github.pagehelper.Page<ServerAdminInfo> listServerAdmin = 
				(com.github.pagehelper.Page<ServerAdminInfo>) serverAdminMapper.listServerAdmin(spi);
		//System.err.println(listServerAdmin.getResult().get(0));
		//把所属项目转为中文
		List<ServerAdminInfo> list=listServerAdmin.getResult();
		for (int i = 0; i < list.size(); i++) {
			ServerAdminInfo info = list.get(i);
			String applyProjectsName="";
			List<String> applyProjects=new ArrayList<>();
			String project=info.getApplyProjects();
			//如果所属项目不为空
			if (project!=null&&!StringUtils.isEmpty(project)) {
				String applyProject[]=project.split(",");
				for (int j = 0; j < applyProject.length; j++) {
					String parmCode=applyProject[j];
					applyProjects.add(parmCode);
					List<Map<String, Object>> proList=iSysParameterService.findParameterByParmCodeOrParmName("projectName",parmCode,null);
					if (proList.size()>0) {
						if (j==applyProject.length-1) {
							applyProjectsName+=proList.get(0).get("value");
						}else {
							applyProjectsName+=proList.get(0).get("value")+",";
						}
					}
				}
			}
			//匹配到中文名称后重新放入对象applyProjects
			info.setApplyProjectsName(applyProjectsName);
			info.setProjects(applyProjects);
			list.set(i, info);
		}
		Page<ServerAdminInfo> page = new Page<ServerAdminInfo>();
		page.setData(listServerAdmin.getResult());
		page.setPageNo(listServerAdmin.getPageNum());
		page.setPageSize(listServerAdmin.getPageSize());
		page.setTotalCount((int) listServerAdmin.getTotal());
		return page;
	}

	@Override
	public void updateServerStatus(ServerStatus status) {

	}

	@Override
	public ServerAdminInfo getServerInfoByServerId(Long serverId) {
		ServerAdmin sAdmin = serverAdminMapper.findServerByServerId(serverId);
		ServerAdminInfo sai = new ServerAdminInfo();
		sai.setUsername(sAdmin.getUsername());
		return sai;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IServerAdminService#addEntity(com.mhtech.platform.msrv.pojo.ServerInfo)
	 */
	@Override
	public int addEntity(ServerInfo server) {
		int flg=serverAdminMapper.insert(server);
		return flg;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IServerAdminService#findServerInfoByServerName(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> findServerInfoByServerName(String serverName) {
		List<Map<String, Object>> list=serverAdminMapper.findServerInfoByServerName(serverName);
		return list;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IServerAdminService#delById(java.lang.String)
	 */
	@Override
	public void delById(String id) {
		// TODO 自动生成的方法存根
		serverAdminMapper.delById(Long.parseLong(id));
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IServerAdminService#findServerInfoByServerNameForId(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> findServerInfoByServerNameForId(String serverName) {
		List<Map<String, Object>> list=serverAdminMapper.findServerInfoByServerNameForId(serverName);
		return list;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IServerAdminService#deleteByIds(java.util.List)
	 */
	@Override
	public int deleteByIds(List<String> list) {
		return serverAdminMapper.deleteByIds(list);
	}

	@Override
	public ServerAdminInfo getServerInfoByPrimaryKey(Long serverId) {
		return null;
	}
}
