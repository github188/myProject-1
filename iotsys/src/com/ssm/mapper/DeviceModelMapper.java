package com.ssm.mapper;

import java.util.List;

import com.ssm.beans.DeviceModelBean;

public interface DeviceModelMapper {
	
	public List<DeviceModelBean> findAll()throws Exception;
	
}
