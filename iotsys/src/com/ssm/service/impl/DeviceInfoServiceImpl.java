package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.DeviceInfoBean;
import com.ssm.mapper.DeviceInfoMapper;
import com.ssm.service.IDeviceInfoService;

@Service("deviceInfoService")
public class DeviceInfoServiceImpl  implements IDeviceInfoService{
	@Resource
	private DeviceInfoMapper deviceInfoDao;

	@Resource
	private DeviceInfoMapper um;
	
	@Override
	public List<DeviceInfoBean> findAll() throws Exception {
		return deviceInfoDao.findAll();
	}
	
	
	public List<DeviceInfoBean> getDeviceAndProbeList(Map<String, String> params)
			throws Exception {
		return um.getDeviceAndProbeList(params);
	}


	@Override
	public Integer insertDevice(DeviceInfoBean deviceInfoBean) throws Exception {
		deviceInfoDao.insertDevice(deviceInfoBean);
		return null;
	}


	@Override
	public DeviceInfoBean findById(String deviceId) throws Exception {
		return deviceInfoDao.findById(deviceId);
	}


	@Override
	public List<DeviceInfoBean> findByEnterpriseIdAndName(
			Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return deviceInfoDao.findByEnterpriseIdAndName(params);
	}
	@Override
	public void updateDevice(DeviceInfoBean deviceInfo) throws Exception {
		deviceInfoDao.updateDevice(deviceInfo);
	}


	@Override
	public List<DeviceInfoBean> findByCondition(Map<String,Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return deviceInfoDao.findByCondition(params);
	}


	@Override
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		deviceInfoDao.deleteById(id);
	}


	@Override
	public DeviceInfoBean findBySerialNumber(String serialNumber) throws Exception {
		// TODO Auto-generated method stub
		return deviceInfoDao.findBySerialNumber(serialNumber);
	}
}
