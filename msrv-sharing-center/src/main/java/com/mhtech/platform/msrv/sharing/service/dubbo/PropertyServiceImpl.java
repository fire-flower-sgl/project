package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.util.RLEDecompressingInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.pojo.AlertLog;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyHardwareVO;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.sharing.dao.mapper.PropertyHardwareMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.PropertyMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.SpDeviceAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.model.Property;
import com.mhtech.platform.msrv.sharing.dao.model.PropertyHardware;
import com.mhtech.platform.msrv.sharing.service.impl.BaseServiceImpl;
import com.mobile.utils.StringUtils;

@Service("propertyService")
public class PropertyServiceImpl extends BaseServiceImpl implements IPropertyService {
	
	@Autowired
	private PropertyMapper propertyMapper;
	
	@Autowired
	private SpDeviceAdminMapper spDeviceAdminMapper;
	
	@Autowired
	private PropertyHardwareMapper propertyHardwareMapper;
	
	@Autowired
	private IServerAlertRuleService AlertRuleService;
	
	@Autowired
	private IAlertLogService alertLogService;
	
	@Value("${alert.state.ok}")
	private Short noAlartState;
	
	@Autowired
	ISysParameterService iSysParameterService;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = propertyMapper;
		
	}

	@Override
	public Page<PropertyVOId> queryPage(PropertyDTO mt) {
		PageHelper.startPage(mt.getPageNo(),mt.getPageSize());
		List<PropertyVOId> list = propertyMapper.selectEntities(mt);
		PageInfo<PropertyVOId> pageinfo = new PageInfo<PropertyVOId>(list);
		List<PropertyVOId> listVO = new ArrayList<PropertyVOId>();
		if(!CollectionUtils.isEmpty(pageinfo.getList())) {
			for (PropertyVOId hw : pageinfo.getList()) {
				//把id转成string
				PropertyVOId ht = new PropertyVOId(hw.getId(), hw.getCode(), hw.getType(), hw.getName(), hw.getIp(), hw.getFixTime(), hw.getState(), hw.getCreatedTime());
				String applyProjects="";
				String project=hw.getApplyProjects();
				if (project!=null&&!StringUtils.isBlank(project)) {
					String projectOld="";
					String applyProject[]=project.split(",");
					for (int i = 0; i < applyProject.length; i++) {
						String parmCode=applyProject[i];
						//如果项目编码不被包含-没有处理过
						//System.err.println(parmCode+"==="+!projectOld.contains(parmCode)+"==="+projectOld);
						if (!projectOld.contains(parmCode)) {
							List<Map<String, Object>> proList=iSysParameterService.findParameterByParmCodeOrParmName("projectName",parmCode,null);
							if (proList.size()>0) {
								if (i==applyProject.length-1) {
									applyProjects+=proList.get(0).get("value");
								}else {
									applyProjects+=proList.get(0).get("value")+",";
								}
							}
							projectOld+=parmCode+",";
						}
					}
				}
				ht.setApplyProjects(applyProjects);
				ht.setIsMonitor(hw.getIsMonitor());
				ht.setUsername(hw.getUsername());
				ht.setUsercode(hw.getUsercode());
				ht.setStatename(hw.getStatename());
				ht.setTypename(hw.getTypename());
				listVO.add(ht);
			}
		}
		System.err.println(listVO.toString());
		Page page= new Page(pageinfo.getPageSize(),Integer.parseInt(String.valueOf(pageinfo.getTotal())),pageinfo.getStartRow(),mt.getPageNo(),pageinfo.getPages(),listVO);
		return page;
	}

	@Override
	public boolean isExist(PropertyDTO mt) {
		return propertyMapper.isExist(mt)>0;
	}

	@Override
	public PropertyVO findById(Long id) {
		PropertyVOId pr = propertyMapper.findById(id);
		PropertyVO ht = new PropertyVO(Long.parseLong(pr.getId()), pr.getCode(), pr.getType(), pr.getName(), pr.getIp(), pr.getFixTime(), pr.getState().toString(), pr.getCreatedTime(), pr.getApplyProjects(), Short.parseShort(pr.getIsMonitor()));
		String applyProjects="";
		//把项目名去重并且转为中文
		String project=ht.getApplyProjects();
		if (project!=null&&!StringUtils.isBlank(project)) {
			String applyProject[]=project.split(",");
			String projectOld="";
			for (int i = 0; i < applyProject.length; i++) {
				String parmCode=applyProject[i];
				//如果项目编码不被包含-没有处理过
				//System.err.println(parmCode+"==="+!projectOld.contains(parmCode)+"==="+projectOld);
				if (!projectOld.contains(parmCode)) {
					List<Map<String, Object>> proList=iSysParameterService.findParameterByParmCodeOrParmName("projectName",parmCode,null);
					if (proList.size()>0) {
						if (i==applyProject.length-1) {
							applyProjects+=proList.get(0).get("value");
						}else {
							applyProjects+=proList.get(0).get("value")+",";
						}
					}
					projectOld+=parmCode+",";
				}
			}
		}
		ht.setApplyProjects(applyProjects);
		ht.setNotifyCount(propertyMapper.findNotifyCountById(id));
		List<PropertyHardwareVO> data = getHardwareListById(id,ht.getCode());
		ht.setHardwareList(data);
		return ht;
	}
	
	@Override
	public List<PropertyHardwareVO> getHardwareListById(Long id,String code){
		List<PropertyHardwareVO> dataList = new ArrayList<PropertyHardwareVO>();
		List<PropertyHardware> datas = propertyHardwareMapper.selectEntities(code);
		if(datas.size()>0) {
			for (int i = 0; i < datas.size(); i++) {
				PropertyHardware ph = datas.get(i);
				PropertyHardwareVO  phVo = new PropertyHardwareVO(ph.getId(), ph.getPropertyCode(), ph.getHardwareName(), 
						ph.getHardwareType(),ph.getAlias(), ph.getSize(), ph.getDescription(), ph.getState(), ph.getCreatedTime());
				//查询预警等级
				Short checkLevel = checkLevel(id,phVo.getHardwareName());
				phVo.setLevel(checkLevel);
				dataList.add(phVo);
			}	
		}
		return dataList;
	}

	private Short checkLevel(Long id, String hardwareName) {
		Short resule = noAlartState;
		//查询警报规则
		Long serverId = propertyMapper.findServerId(id);
		List<ServerAlertRule> ruleList = AlertRuleService.getServerAlertRuleByParam(serverId, hardwareName);
		System.err.println("rule规则size==="+ruleList.size());
		if(!CollectionUtils.isEmpty(ruleList)) {
			//查询日志
			for (ServerAlertRule rule : ruleList) {
				//查询日志条数的条件
				AlertLogVO alvo = new AlertLogVO();
				alvo.setServerId(rule.getServerId());
				alvo.setParamName(rule.getParamName());
				long currentTime = System.currentTimeMillis();					
				Date nowTime = new Date(currentTime);				
				long startTime = currentTime-1000*rule.getDuration();
				Date startAlertTime = new Date(startTime);
				alvo.setStartAlertTime(startAlertTime);
				alvo.setEndAlertTime(nowTime);
				
				//查询日志count
				List<AlertLog> listAlertLog = alertLogService.listAlertLog(alvo);
				if (CollectionUtils.isEmpty(listAlertLog)) {
					break;
				}
				Integer count = listAlertLog.size();
				
				//比较得到预警等级
				if(count>=rule.getAlertLimit()) {
					resule = rule.getLevel();
					break;
				}	
			}
		}
		return resule;
	}

	@Override
	public int delPreHardware(PropertyDTO mt) {
		return propertyHardwareMapper.delPreHardware(mt);
	}

	@Override
	public int addPreHardware(PropertyDTO mt) {
		propertyHardwareMapper.delAllByPropertyCode(mt.getCode());
		int batchInsert = 0;
		if(!CollectionUtils.isEmpty(mt.getHardWareList()))
				propertyHardwareMapper.batchInsert(mt.getHardWareList());
		return batchInsert;
	}

	@Override
	public int del(Long id) {
		PropertyVOId pr = propertyMapper.selectByPrimaryKey(id);
		int del = propertyMapper.deleteByPrimaryKey(id);
		propertyHardwareMapper.delAllByPropertyCode(pr.getCode());
		spDeviceAdminMapper.deleteById(pr.getCode());
		return del;
	}
	
	@Override
	public List<Map<String, Object>> getProperty(String serverName){
		return propertyMapper.getProperty(serverName);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IPropertyService#ipIsExist(com.mhtech.platform.msrv.pojo.PropertyDTO)
	 */
	@Override
	public boolean ipIsExist(PropertyDTO mt) {
		return propertyMapper.ipIsExist(mt)>0;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IPropertyService#getPropertyNameAndIp(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getPropertyNameAndIp(String serverName) {
		return propertyMapper.getPropertyNameAndIp(serverName);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.IPropertyService#deleteByIds(java.util.List)
	 */
	@Override
	public int deleteByIds(List<String> list) {
		return propertyMapper.deleteByIds(list);
	}


}
