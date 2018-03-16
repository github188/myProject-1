package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.DeviceInfoBean;

public interface DeviceInfoMapper {

	public List<DeviceInfoBean>  findAll() throws Exception;
	
	public Integer insertDevice(DeviceInfoBean deviceInfoBean) throws Exception;
	
	public List<DeviceInfoBean>  getDeviceAndProbeList(Map<String, String> params) throws Exception;

	public DeviceInfoBean findById(String deviceId) throws Exception;

	public void updateDevice(DeviceInfoBean deviceInfo) throws Exception;
	
	public List<DeviceInfoBean> findByEnterpriseIdAndName(Map<String,Object> params) throws Exception;

	public List<DeviceInfoBean> findByCondition(Map<String,Object> params) throws Exception;
	
	public void deleteById(@Param("id")String id) throws Exception;
	
	public DeviceInfoBean findBySerialNumber(@Param("serialNumber") String serialNumber)throws Exception;
	
}
