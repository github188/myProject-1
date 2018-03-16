package com.ssm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.DeviceInfoBean;

public interface IDeviceInfoService {
	
	public List<DeviceInfoBean> findAll() throws Exception;
	
	public Integer insertDevice(DeviceInfoBean deviceInfoBean) throws Exception;

	public List<DeviceInfoBean> getDeviceAndProbeList(Map<String, String> params)throws Exception;

	public DeviceInfoBean findById(String deviceId)throws Exception;

	public void updateDevice(DeviceInfoBean deviceInfo) throws Exception;
	
	/**
	 * 通过企业ID和设备名称模糊查询设备信息
	 * @param params 动态参数
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public List<DeviceInfoBean> findByEnterpriseIdAndName(Map<String,Object> params) throws Exception;

	/**
	 * 条件查询设备
	 * @param enterpriseId
	 * @param aORb
	 * @return
	 * @throws Exception
	 */
	public List<DeviceInfoBean> findByCondition(Map<String,Object> params) throws Exception;
	
	/**
	 * 通过设备ID删除设备
	 * @param id 设备ID
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public void deleteById(String id) throws Exception;
	
	/**
	 * 通过设备序列号查询设备
	 * @param serialNumber 设备序列号
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public DeviceInfoBean  findBySerialNumber(@Param("serialNumber") String serialNumber)throws Exception;
	
}
