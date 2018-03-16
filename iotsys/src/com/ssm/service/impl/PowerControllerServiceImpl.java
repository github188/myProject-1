package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.PowerControllerBean;
import com.ssm.mapper.PowerControllerMapper;
import com.ssm.service.IPowerControllerService;

@Service("powerControllerService")
public class PowerControllerServiceImpl implements IPowerControllerService {

	@Resource
	private PowerControllerMapper  powerControllerMapper;
	
	@Override
	public List<PowerControllerBean> findPowerByDeviceSerialNumber(
			String serial_number) throws Exception {
		// TODO Auto-generated method stub
		return powerControllerMapper.findPowerByDeviceSerialNumber(serial_number);
	}

	@Override
	public int BatchInsert(List<PowerControllerBean> Controllers)
			throws Exception {
		// TODO Auto-generated method stub
		powerControllerMapper.BatchInsert(Controllers);
		return 0;
	}

	@Override
	public void UpdateBatchControllers(List<PowerControllerBean> Controllers)
			throws Exception {
		// TODO Auto-generated method stub
		powerControllerMapper.UpdateBatchControllers(Controllers);
		
	}

	@Override
	public void controllerTurnOnOff(Map<String, String> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		powerControllerMapper.controllerTurnOnOff(paramMap); 
	}

	@Override
	public void deleteByDeviceSer(String serialNumber) throws Exception {
		// TODO Auto-generated method stub
		powerControllerMapper.deleteByDeviceSer(serialNumber); 
	}

	@Override
	public void deleteByIds(List<String> deleteIds) throws Exception {
		// TODO Auto-generated method stub
		powerControllerMapper.deleteByIds(deleteIds);
	}

}
