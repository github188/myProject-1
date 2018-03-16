package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.DeviceModelBean;
import com.ssm.mapper.DeviceModelMapper;
import com.ssm.service.IDeviceModelService;

@Service("deviceModelService")
public class DeviceModelServiceImpl  implements IDeviceModelService{
	@Resource
	private DeviceModelMapper deviceModelDao;



	@Override
	public List<DeviceModelBean> findAll() throws Exception {
		return deviceModelDao.findAll();
	}
}
