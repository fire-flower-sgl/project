package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.request.ReqHardwareTypeAdd;
import com.mhtech.platform.msrv.gateway.request.ReqHardwareTypeDel;
import com.mhtech.platform.msrv.gateway.request.ReqHardwareTypePage;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IHardwareTypeService;
import com.mhtech.platform.msrv.monitor.service.IMonitorPlansService;
import com.mhtech.platform.msrv.pojo.HardwareTypeDTO;
import com.mhtech.platform.msrv.pojo.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 硬件类型控制层
 * @Author admin
 * @Date 2020/3/19 10:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/hardwareType")
@Api(value = "HardwareTypeController", tags = "硬件模块")
public class HardwareTypeController {
	
	@Autowired
	private IHardwareTypeService hardwareType;
	
	/**
	 * 分页查询
	 * @param reqHT
	 * @return
	 */
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询")
    public RespObject<?> queryPage(@RequestBody @Valid ReqHardwareTypePage reqHT) {
		HardwareTypeDTO mt = new HardwareTypeDTO();
		mt.setPageNo(reqHT.getPageNo());
		mt.setPageSize(reqHT.getPageSize());
		Page page = hardwareType.queryPage(mt);
        return RespUtils.buildOKWithData(page);
	}
	
	/**
	 * 新增
	 * @param reqHT
	 * @return
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "新增")
    public RespObject<?> add(@RequestBody @Valid ReqHardwareTypeAdd reqHT) {
		HardwareTypeDTO mt = reqObjToDTO(reqHT);
		boolean flag = hardwareType.isExist(mt);
		if(flag) {
			throw new RuntimeException("资产编码重复");
		}
		int add = hardwareType.save(Arrays.asList(mt));
        return RespUtils.buildOKWithData(add);
	}
	
	/**
	 * 更新
	 * @param reqHT
	 * @return
	 */
	@PostMapping(value = "/modify")
	@ApiOperation(value = "根据id更新")
    public RespObject<?> modify(@RequestBody @Valid ReqHardwareTypeAdd reqHT) {
		HardwareTypeDTO mt = reqObjToDTO(reqHT);
		boolean flag = hardwareType.isExist(mt);
		if(flag) {
			throw new RuntimeException("资产编码重复");
		}
		int modify = hardwareType.modifyEntityByKey(mt);
        return RespUtils.buildOKWithData(modify);
	}	
	
	/**
	 * 删除
	 * @param reqHT
	 * @return
	 */
	@PostMapping(value = "/del")
	@ApiOperation(value = "根据id删除")
    public RespObject<?> del(@RequestBody @Valid ReqHardwareTypeDel reqHT) {
		int flag = hardwareType.remove(reqHT.getTypeCode());
        return RespUtils.buildOKWithData(flag);
	}
	
	
	/**
	 * 请求实体类转换成DTO实体
	 * @param rscr
	 * @return
	 */
	private HardwareTypeDTO reqObjToDTO(ReqHardwareTypeAdd mp) {
		HardwareTypeDTO  mpdto = new HardwareTypeDTO(mp.getTypeCode(), mp.getTypeAlias(), mp.getUnit(), mp.getMemo());
		return mpdto;
	}	
}
