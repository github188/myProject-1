package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.MassifProductbean;
import com.ssm.mapper.MassIfProductMapper;
import com.ssm.service.IMassIfProductService;
@Service("massIfProductService")
public class MassIfProductServiceImpl implements IMassIfProductService{
	@Resource
	private MassIfProductMapper massMapper;
	@Override
	public void updataMassifProduct(MassifProductbean massifProductbean) throws Exception {
		
		massMapper.updataMassifBase(massifProductbean);
	}
	@Override
	public void addMassifProduct(MassifProductbean massifProductbean)
			throws Exception {
		massMapper.insertMassifProduct(massifProductbean);
		
	}
	@Override
	public void updateMassifMass(MassifProductbean massifProductbean)
			throws Exception {
		massMapper.updataMassifMass(massifProductbean);
	}
	@Override
	public List<MassifProductbean> findByBaseName(String baseName)
			throws Exception {
		List<MassifProductbean> selectMassByName = massMapper.selectMassByName(baseName);
		if (null!=selectMassByName&&selectMassByName.size()>0) {
			return selectMassByName;
		}else {
			return null;
		}
	}
	

	@Resource
	private MassIfProductMapper um;
	public MassIfProductMapper getUm() {
		return um;
	}
	public void setUm(MassIfProductMapper um) {
		this.um = um;
	}
	@Override
	public List<MassifProductbean> getMassifAndProbeData(Map<String,String> paramMap) {
		return um.getMassifAndProbeData(paramMap);
	}
	
	@Override
	public List<MassifProductbean> getMassifAndDeviceList(Map<String,Object> paramMap) {
		return um.getMassifAndDeviceList(paramMap);
	}
	
	@Override
	public List<MassifProductbean> getDeviceListForMassif(Map<String,Object> paramMap) {
		return um.getDeviceListForMassif(paramMap);
	}
	
	@Override
	public void updateMassifBaseEnter(Map<String, String> map) throws Exception {
		massMapper.updateMassIfBaseEnter(map);
	}

	@Override
	public List<MassifProductbean> selectMassifProductByEnterpriseId(String enetrpriseId) throws Exception{
		// TODO Auto-generated method stub
		return um.selectMassifProductByEnterpriseId(enetrpriseId);
	}
	@Override
	public List<MassifProductbean> selectMassifProductByEnterpriseIdAndBaseId(String enterpriseId, String baseId) throws Exception{
		// TODO Auto-generated method stub
		return um.selectMassifProductByEnterpriseIdAndBaseId(enterpriseId,baseId);
	}
	
	@Override
	public List<MassifProductbean> getBaseAndMassif(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return um.getBaseAndMassif(paramMap);
	}
	@Override
	public List<MassifProductbean> getBase(Map<String, Object> paramMap) {
		List<MassifProductbean> list=new ArrayList<MassifProductbean>();
		try {
			list= um.getBase(paramMap);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<MassifProductbean> getMassif(String baseId) {
		List<MassifProductbean> list=new ArrayList<MassifProductbean>();
		try {
			list= um.getMassif(baseId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public MassifProductbean getMassifAndDeviceListById(String id)
			throws Exception {
		// TODO Auto-generated method stub
		return um.getMassifAndDeviceListById(id);
	}
	@Override
	public List<MassifProductbean> getBaseAndMassifList(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		return um.getBaseAndMassifList(mapParam);
	}
	@Override
	public List<MassifProductbean> CountDeviceNumber(String enterpriseId)throws Exception {
		// TODO Auto-generated method stub
		return um.CountDeviceNumber(enterpriseId);
	}
	
	public MassifProductbean getMassifAndProbeDataByDeviceSer(String deviceId) throws Exception {
		// TODO Auto-generated method stub
		return um.getMassifAndProbeDataByDeviceSer(deviceId);
	}
	public void updateMP(MassifProductbean massifProductbean) throws Exception {
		um.updateMP(massifProductbean);
	}
	public void deleteMPByDeviceSer(String string) throws Exception {
		// TODO Auto-generated method stub
		um.deleteMPByDeviceSer(string);
	}
	@Override
	public void updateMassifProductByBaseId(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		um.updateMassifProductByBaseId(map);
	}
	@Override
	public void updateMassifProductByMassifId(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		um.updateMassifProductByMassifId(map);
	}
	
}
