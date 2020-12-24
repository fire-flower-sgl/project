package com.mhtech.platform.msrv.gateway.controller.algorithm;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.IAlProjectMemberService;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAlTeamProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.InsertListProjectMemberParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.InsertProjectMemberParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.UpdateProjectMemberParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.AlProjectMemberDTO;
import com.mhtech.platform.msrv.pojo.AlProjectMemberVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 5G算法中心-项目成员
 * 
 * @author Administrator
 * 
 */

@RestController
@RequestMapping(value = "/algorithm/projectMember")
@Api(value = "AlProjectMemberController", tags = "5G算法中心项目成员")
public class AlProjectMemberController {

	@Autowired
	IAlProjectMemberService iAlProjectMemberService;

	@PostMapping("/findProjectMember")
	@ApiOperation(value = "查询项目成员", notes = "查询项目成员信息")
	public RespObject<?> findProjectMember(@RequestBody @Valid FindAlTeamProjectParams fs) {
		AlProjectMemberVO vo;
		try {
			vo = iAlProjectMemberService.selectId(fs.getId());
		} catch (Exception e) {
			throw new RuntimeException("查询项目成员信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/insertProjectMember")
	@ApiOperation(value = "新增项目成员", notes = "新增项目成员")
	public RespObject<?> insertProjectMember(@RequestBody @Valid InsertProjectMemberParams ip) {

		AlProjectMemberDTO dto = new AlProjectMemberDTO(ip.getPrjCode(), ip.getUserCode(), ip.getTeamRole());
		int insert = 0;
		try {
			insert = iAlProjectMemberService.insert(dto);
		} catch (Exception e) {
			throw new RuntimeException("新增团队成员异常出错");
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/insertListProjectMember")
	@ApiOperation(value = "批量新增项目成员", notes = "批量新增项目成员")
	@Transactional
	public RespObject<?> insertListProjectMember(@RequestBody @Valid InsertListProjectMemberParams ip) {
		if (ip.getPrjCode()!=null) {
			 int  delectList = iAlProjectMemberService.delectList(ip.getPrjCode());
			 return RespUtils.buildOKWithDataYg(delectList);
		}else {
			if (!ip.getProjectMember().isEmpty()) {
				//先删除
				iAlProjectMemberService.delectList(ip.getProjectMember().get(0).getPrjCode());
			}
			List<AlProjectMemberDTO> dto = new ArrayList<AlProjectMemberDTO>();
			ip.getProjectMember().forEach(sp -> {
				dto.add(new AlProjectMemberDTO(sp.getPrjCode(), sp.getUserCode(), sp.getTeamRole()));
			});
			int insert = 0;
			try {
				if (!dto.isEmpty()) {
					insert = iAlProjectMemberService.insertList(dto);
				}
			} catch (Exception e) {
				throw new RuntimeException("批量新增项目成员异常出错");
			}
			return RespUtils.buildOKWithDataYg(insert);
		}
		
	}

	@PostMapping("/updateProjectMember")
	@ApiOperation(value = "更新项目成员", notes = "更新项目成员")
	public RespObject<?> updateProjectMember(@RequestBody @Valid UpdateProjectMemberParams up) {

		AlProjectMemberDTO dto = new AlProjectMemberDTO(up.getId(), up.getPrjCode(), up.getUserCode(), up.getTeamRole(),
				up.getCreatedTime());
		int update;
		try {
			update = iAlProjectMemberService.update(dto);
		} catch (Exception e) {
			throw new RuntimeException("更新项目成员异常出错");
		}
		return RespUtils.buildOKWithDataYg(update);
	}

	@PostMapping("/delectProjectMember")
	@ApiOperation(value = "删除项目成员", notes = "删除项目成员")
	public RespObject<?> delectProjectMember(@RequestBody @Valid FindAlTeamProjectParams fs) {
		int key;
		try {
			key = iAlProjectMemberService.delete(fs.getId());
		} catch (Exception e) {
			throw new RuntimeException("删除项目成员异常出错");
		}
		return RespUtils.buildOKWithDataYg(key);
	}

	
	
}
