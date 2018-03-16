package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.beans.PowerControllerBean;

public interface IPowerControllerService {
	
	/**
	 * 通过设备ID查找控制器
	 * @param device_id
	 * @throws Exception
	 */
	public List<PowerControllerBean> findPowerByDeviceSerialNumber(String serial_number) throws Exception;
	/**
	 * 批量插入控制器
	 * @param device_id
	 * @throws Exception
	 */
	public int BatchInsert(List<PowerControllerBean> Controllers) throws Exception;
	
	/**
	 * 批量更新控制器信息
	 * @param Controllers 控制器集
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public void UpdateBatchControllers(List<PowerControllerBean> Controllers)throws Exception;
	
	/**
	 * 控制器的开关
	 * @author mmy
	 * @creatTime 2018年1月25日 下午4:35:15
	 * @param paramMap
	 * @throws Exception
	 */
	public void controllerTurnOnOff(Map<String,String> paramMap)throws Exception;
	public void deleteByDeviceSer(String serialNumber) throws Exception;
	public void deleteByIds(List<String> deleteIds) throws Exception;
	
}
