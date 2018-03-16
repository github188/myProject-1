package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.PowerControllerBean;

public interface PowerControllerMapper {
	public List<PowerControllerBean> findPowerByDeviceSerialNumber(String serial_number) throws Exception;
	
	public int BatchInsert(List<PowerControllerBean> Controllers) throws Exception;
	
	public void UpdateBatchControllers(List<PowerControllerBean> Controllers)throws Exception;
	
	public void controllerTurnOnOff(Map<String,String> paramMap)throws Exception;

	public void deleteByDeviceSer(@Param("serialNumber")String serialNumber)throws Exception;

	public void deleteByIds(@Param("ids")List<String> ids) throws Exception;
	
}
