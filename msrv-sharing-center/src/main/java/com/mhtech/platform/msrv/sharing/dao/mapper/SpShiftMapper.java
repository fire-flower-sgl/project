package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.pojo.SpUserShift;

@Mapper
public interface SpShiftMapper extends GenericMapper{
   
	List<SpUserShift> listUserOnDuty(String serverIp);
}