package com.mhtech.platform.msrv.gateway.controller.algorithm;

import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.IAlProjectMemberService;
import com.mhtech.platform.msrv.algorithm.service.IAlTeamMemberService;
import com.mhtech.platform.msrv.gateway.request.algorithm.DelectMemberParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAlTeamProjectParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAllMemberParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.InsertMemberParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.UpdateMemberParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.AlTeamMemberDTO;
import com.mhtech.platform.msrv.pojo.AlTeamMemberVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 5G算法中心-团队成员
 * 
 * @author Administrator
 * 
 */


@RestController
@RequestMapping(value = "/algorithm/alTeamMember")
@Api(value = "AlTeamMemberController", tags = "5G算法中心团队成员")
public class AlTeamMemberController {

	@Autowired
	IAlTeamMemberService iAlTeamMemberService;
	@Autowired
	IAlProjectMemberService iAlProjectMemberService;

	@PostMapping("/findMember")
	@ApiOperation(value = "查询团队成员")
	public RespObject<?> findMember(@RequestBody @Valid FindAlTeamProjectParams fs) {
		AlTeamMemberVO vo;
		try {
			vo = iAlTeamMemberService.selectId(fs.getId());
		} catch (Exception e) {
			throw new RuntimeException("查询团队成员信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/findAllMember")
	@ApiOperation(value = "查询所有团队成员-支持名称模糊查询")
	public RespObject<?> findAllMember(@RequestBody @Valid FindAllMemberParams fs) {
		List<AlTeamMemberVO> vo;
		try {
			vo = iAlTeamMemberService.listName(fs.getNickname());
		} catch (Exception e) {
			throw new RuntimeException("查询所有团队成员-支持名称模糊查询-异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}
	
	@PostMapping("/findMemberNamelist")
	@ApiOperation(value = "查询团队成员所有名称与编码")
	public RespObject<?> findMemberNamelist() {
		List<AlTeamMemberVO> vo;
		try {
			vo = iAlTeamMemberService.listProject();
		} catch (Exception e) {
			throw new RuntimeException("查询团队成员所有名称与编码-异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}


	@PostMapping("/insertMember")
	@ApiOperation(value = "新增团队成员")
	public RespObject<?> insertMember(@RequestBody @Valid InsertMemberParams imp) {
		if (iAlTeamMemberService.nameOnly(imp.getUserCode())) {
			return RespUtils.build(SUCCESS.getCode(), false, SUCCESS.getMessage(), "新增团队成员出错-用户编码已存在");
		}
		AlTeamMemberDTO dto = new AlTeamMemberDTO(imp.getTeamCode(), imp.getUserCode(), imp.getNickname(),
				imp.getEmail(), imp.getIcon());
		int insert = 0;
		try {
			insert = iAlTeamMemberService.insert(dto);
		} catch (Exception e) {
			throw new RuntimeException("新增团队成员异常出错");
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/updateMember")
	@ApiOperation(value = "更新团队成员")
	public RespObject<?> updateMember(@RequestBody @Valid UpdateMemberParams ump) {
		AlTeamMemberDTO dto = new AlTeamMemberDTO(ump.getId(), ump.getTeamCode(), ump.getUserCode(), ump.getNickname(),
				ump.getEmail(), ump.getCreatedTime(), ump.getIcon());
		int update;
		try {
			update = iAlTeamMemberService.update(dto);
		} catch (Exception e) {
			throw new RuntimeException("更新团队成员异常出错");
		}
		return RespUtils.buildOKWithDataYg(update);
	}

	@PostMapping("/delectMember")
	@ApiOperation(value = "删除团队成员-并删除关联项目信息")
	@Transactional
	public RespObject<?> delectMember(@RequestBody @Valid DelectMemberParams fs) {
		List<Map<String,Integer>> list=new ArrayList<Map<String,Integer>>();
		Map<String,Integer> map=new HashMap<>();
		int key1;
		int key2;
		try {
			key1 = iAlTeamMemberService.delete(fs.getId());
		    key2 = iAlProjectMemberService.delectUserCode(fs.getUserCode());
		    map.put("删除团队成员信息条数", key1);
		    map.put("删除团队成员关联项目信息条数", key2);
		    list.add(map);
		} catch (Exception e) {
			throw new RuntimeException("删除团队成员-并删除关联项目信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(list);
	}
	
	
	
	
	
	
}
