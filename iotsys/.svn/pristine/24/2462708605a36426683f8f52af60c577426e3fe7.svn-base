package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.CommonBean;
import com.ssm.beans.ProbeBean;
import com.ssm.mapper.ProbeMapper;
import com.ssm.service.IProbeService;

@Service("probeServiceImpl")
public class ProbeServiceImpl  implements IProbeService{
	
	@Resource
	private ProbeMapper um;
	@Override
	public List<ProbeBean> getProbeAndProbeData(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		return um.getProbeAndProbeData(params);
	}
	@Override
	public void insertMany(List<ProbeBean> probeBeans) throws Exception {
		this.um.insertMany(probeBeans);
	}
	@Override
	public List<ProbeBean> findByDeviceSerialNumber(String serialNumber)
			throws Exception {
		// TODO Auto-generated method stub
		return this.um.findByDeviceSerialNumber(serialNumber);
	}
	
	@Override
	public void updateBatch(List<ProbeBean> list) throws Exception {
		this.um.updateBatch(list);
		
	}
	@Override
	public void updateProbe(ProbeBean probeBeans) throws Exception {
		um.updateProbe(probeBeans);
	}
	
	@Override
	public 	CommonBean  getSpecificData(Map<String,String> probeBeans)
			throws Exception {
		return um.getSpecificData(probeBeans);
	}
	@Override
	public 	CommonBean  getSpecificDataCountForPie(Map<String,String> probeBeans)
			throws Exception {
		return um.getSpecificDataCountForPie(probeBeans);
	}
	@Override
	public void deleteByDeviceSer(String serialNumber) throws Exception {
		// TODO Auto-generated method stub
		um.deleteByDeviceSer(serialNumber);
	}
	
	@Override
	public void deleteByIds(List<String> deleteIds) throws Exception {
		// TODO Auto-generated method stub
		um.deleteByIds(deleteIds);
	}
}
