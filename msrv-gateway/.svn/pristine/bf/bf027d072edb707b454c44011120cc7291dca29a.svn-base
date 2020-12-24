package com.mhtech.platform.msrv.gateway.controller.algorithm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.ByteArrayInputStream;
import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mhtech.platform.msrv.algorithm.service.IAlProjectLogMapperService;
import com.mhtech.platform.msrv.algorithm.service.IAlTeamMemberService;
import com.mhtech.platform.msrv.algorithm.service.IProjectDetailsHomepage;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.request.algorithm.DelectImgParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.DelectPM;
import com.mhtech.platform.msrv.gateway.request.algorithm.DelectProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAlTeamProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAllProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.InsertProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.ProjectFileParam;
import com.mhtech.platform.msrv.gateway.request.algorithm.UpdateProjectParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mhtech.platform.msrv.pojo.AlProjectLogDTO;
import com.mhtech.platform.msrv.pojo.AlTeamMemberVO;
import com.mhtech.platform.msrv.pojo.AlTeamProjectDTO;
import com.mhtech.platform.msrv.pojo.AlTeamProjectVO;
import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.ProjectAnalyzeFlows;
import com.mhtech.platform.msrv.pojo.ProjectExportParam;
import com.mhtech.platform.msrv.pojo.ProjectFile;

/**
 * 5G算法中心-项目详情-首页
 * 项目管理对应 al_team_project 表
 * @author Administrator
 * 
 */

@Api(value = "AlTeamProjectController", tags = "5G算法中心项目详情-首页")
@RestController
@RequestMapping(value = "/algorithm/alTeamProject")
public class AlTeamProjectController {

	@Autowired
	IAlProjectLogMapperService projectLog;
	@Autowired
	IProjectDetailsHomepage iProjectDetailsHomepage;
	@Autowired
	IAlTeamMemberService iAlTeamMemberService;

	@Autowired
	IworkService iworkService;

	@PostMapping("/findAlTeamProject")
	@ApiOperation("查询项目信息及其项目成员")
	public RespObject<?> findAlTeamProject(@RequestBody @Valid FindAlTeamProjectParams fs) {
		AlTeamProjectVO vo = new AlTeamProjectVO();
		try {
			vo = iProjectDetailsHomepage.selectProjectAndMember(fs.getId());
		} catch (Exception e) {
			throw new RuntimeException("查询项目信息及其项目成员异常出错。");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/findProject")
	@ApiOperation("查询项目信息")
	public RespObject<?> findProject(@RequestBody @Valid FindAlTeamProjectParams fs) {
		AlTeamProjectVO vo = new AlTeamProjectVO();
		try {
			vo = iProjectDetailsHomepage.select(fs.getId());
		} catch (Exception e) {
//			throw new RuntimeException("查询项目信息异常出错。");
			e.printStackTrace();
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/findAllProject")
	@ApiOperation("查询所有项目信息")
	public RespObject<?> findAllProject(@RequestBody @Valid FindAllProjectParams fs) {
		AlTeamProjectDTO dto = new AlTeamProjectDTO(fs.getProjectName(), fs.getIsOpen(), fs.getTags());
		List<AlTeamProjectVO> vo = null;
		try {
			vo = iProjectDetailsHomepage.list(dto);
			for (int i = 0; i < vo.size(); i++) {
				  Long id = vo.get(i).getId();
				  String userCode = vo.get(i).getCreator();
				  List<AlTeamMemberVO> imgs = iAlTeamMemberService.listProjectImg(id);//成员图片
				  AlTeamMemberVO creatorImg = iAlTeamMemberService.vo(userCode);//创建者图片
				  vo.get(i).setAlTeamMemberVOs(imgs);
				  vo.get(i).setCreatorImg(creatorImg.getIcon());
			}
		} catch (Exception e) {
//			throw new RuntimeException("查询所有项目信息，异常出错。");
			 e.printStackTrace();
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/insertProject")
	@ApiOperation("新增项目信息")
	public RespObject<?> insertProject(@RequestBody @Valid InsertProjectParams ipp) {

		if (iProjectDetailsHomepage.prjCodeOnly(ipp.getPrjCode())) {
			return RespUtils.build(SUCCESS.getCode(), false, SUCCESS.getMessage(), "新增项目信息失败-项目编码已存在");
		}
		String tag = StringUtils.join(ipp.getTags(), ",");
		// 保存数据
		AlTeamProjectDTO dto = new AlTeamProjectDTO(ipp.getProjectName(), ipp.getPrjCode(), ipp.getIcon(),
				ipp.getIsOpen(), ipp.getDescription(), tag, ipp.getVersion(), ipp.getTeamCode(), ipp.getState(),
				ipp.getIsDeleted(), ipp.getCreator());
		int insert = 0;
		try {
			insert = iProjectDetailsHomepage.insertProject(dto);
			//增加日志
			if (insert>0) {
				AlProjectLogDTO log = new AlProjectLogDTO(ipp.getModelId(), ipp.getCreator(), "新增了一个项目",
						ipp.getPrjCode());
				projectLog.insert(log);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("新增项目信息，异常出错。");
			// e.printStackTrace();
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/imgProject")
	@ApiOperation("上传头像图片")
	public RespObject<?> imgProject(@RequestParam("file") MultipartFile file, InputStream inputStream,
			HttpServletRequest request) {

		String uuid = UUID.randomUUID().toString().trim();
		String fileN = file.getOriginalFilename();
		int index = fileN.indexOf(".");
		String fileName = uuid + fileN.substring(index);
		try {
			File fileMkdir = new File("E:\\msrvImgs\\algorithm\\");

			if (!fileMkdir.exists()) {
				fileMkdir.mkdir();
			}
			// 定义输出流 将文件保存在D盘 file.getOriginalFilename()为获得文件的名字
			FileOutputStream os = new FileOutputStream(fileMkdir.getPath() + "\\" + fileName);
			InputStream in = file.getInputStream();
			int b = 0;
			while ((b = in.read()) != -1) { // 读取文件
				os.write(b);
			}
			os.flush(); // 关闭流
			in.close();
			os.close();
		} catch (Exception e) {
			return RespUtils.buildOKWithData("上传失败");
		}
		return RespUtils.buildOKWithDataYg(fileName);
	}

	@PostMapping("/delectImg")
	@ApiOperation("删除头像图片")
	public RespObject<?> delectImg(@RequestBody @Valid DelectImgParams dp) {
		File f = new File("E:\\msrvImgs\\algorithm\\" + dp.getImgUrl());
		boolean delete = false;
		try {
			delete = f.delete();
		} catch (Exception e) {
			throw new RuntimeException("删除图片异常出错。");
		}
		return RespUtils.buildOKWithDataYg(delete);
	}

	@PostMapping("/updateProject")
	@ApiOperation("更新项目信息")
	public RespObject<?> updateProject(@RequestBody @Valid UpdateProjectParams upp) {
		String tag = StringUtils.join(upp.getTags(), ",");
		AlTeamProjectDTO dto = new AlTeamProjectDTO(upp.getProjectName(), upp.getId(), upp.getPrjCode(), upp.getIcon(),
				upp.getIsOpen(), upp.getDescription(), tag, upp.getVersion(), upp.getTeamCode(), upp.getState(),
				upp.getIsDeleted(), upp.getUserCode(), upp.getCreatedTime());
		int update = 0;
		try {
			update = iProjectDetailsHomepage.updateProject(dto);
			if (update>0) {
				AlProjectLogDTO log = new AlProjectLogDTO(upp.getModelId(), upp.getUserCode(), "更新了项目信息",
						upp.getPrjCode());
				projectLog.insert(log);
			}
		} catch (Exception e) {
//			throw new RuntimeException("更新项目信息，异常出错。");
			e.printStackTrace();
		}
		return RespUtils.buildOKWithDataYg(update);
	}

	@PostMapping("/delectProject")
	@ApiOperation("删除项目")
	public RespObject<?> delectProject(@RequestBody @Valid DelectProjectParams fs) {
		
		String userCode = fs.getUserCode();
		
		int key = 0;
		try {
			key = iProjectDetailsHomepage.deleteProject(fs.getId());
			if (key>0) {
				List<AlProjectLogDTO> list=new ArrayList<>(); 
				for (int i = 0; i < fs.getList().size(); i++) {
					 DelectPM pm = fs.getList().get(i);
					 list.add(new AlProjectLogDTO(pm.getModelId(),userCode,"删除了项目",pm.getPrjCode()));
				}
				System.err.println("----------------"+list);
				projectLog.insertList(list);
			}
		} catch (Exception e) {
			throw new RuntimeException("删除项目，异常出错。");
		}
		return RespUtils.buildOKWithDataYg(key);
	}

	@PostMapping("/findTages")
	@ApiOperation("查询所有标签")
	public RespObject<?> findTages() {
		List<String> list = new ArrayList<String>();
		List<String> tags = iProjectDetailsHomepage.tags();
		// string-转数组-并去重
		tags.forEach(sp -> {
			String[] split = sp.split(",");
			for (int j = 0; j < split.length; j++) {
				if (!list.contains(split[j])) {
					list.add(split[j]);
				}
			}
		});
		return RespUtils.buildOKWithDataYg(list);
	}

	@ApiOperation("导入项目")
	@PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> importTeamProject(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @Validated ProjectFileParam param) {
		try {
			CurrentUser user = TokenUtils.getCurrentUser(token);
			if(Objects.isNull(user)) {
				throw new RuntimeException("未识别用户");
			}
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(param.getUploadFile().getBytes());
			ObjectInputStream objectInputStream = null;
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			ProjectAnalyzeFlows prjFlows = (ProjectAnalyzeFlows) objectInputStream.readObject();
			prjFlows.setPrjName(param.getPrjName());
			iProjectDetailsHomepage.importTeamProject(prjFlows, user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return RespUtils.OK;
	}
	
	@ApiOperation("导出项目")
	@PostMapping(value = "export")
	public void exportTeamProject(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated ProjectExportParam param,
			HttpServletResponse resp) throws IOException {
		CurrentUser user = TokenUtils.getCurrentUser(token);
		if(Objects.isNull(user)) {
			throw new RuntimeException("未识别用户");
		}
		param.setUserCode(user.getUserCode());
		ProjectFile file = iProjectDetailsHomepage.exportTeamProject(param);
		resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		resp.getOutputStream().write(file.getContent());
		resp.setHeader("Access-Control-Expose-Headers","Content-Disposition");
		resp.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getFileName(), "UTF-8") + ".prj");
	}
}
