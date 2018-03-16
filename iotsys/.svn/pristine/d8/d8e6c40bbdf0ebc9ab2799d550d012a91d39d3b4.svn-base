package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.MassifProductbean;

public interface MassIfProductMapper {

	public void updataMassifBase(MassifProductbean massifProductbean) throws Exception;
	
	public void updataMassifMass(MassifProductbean massifProductbean) throws Exception;
	
	public List<MassifProductbean> getMassifAndProbeData(String id);
	
	public void insertMassifProduct(MassifProductbean massifProductbean) throws Exception;

	public List<MassifProductbean> getMassifAndProbeData(Map<String,String> paramMap);
	
	public List<MassifProductbean> getMassifAndDeviceList(Map<String,Object> paramMap);
	
	public List<MassifProductbean> getDeviceListForMassif(Map<String,Object> paramMap);
	
	public List<MassifProductbean> selectMassByName(@Param("baseName")String baseName);
	
	public void updateMassIfBaseEnter(Map<String, String> map) throws Exception;
	
	public List<MassifProductbean> selectMassifProductByEnterpriseId(String enterpriseId) throws Exception;
	
	public List<MassifProductbean> selectMassifProductByEnterpriseIdAndBaseId(@Param("enterpriseId")String enterpriseId,@Param("baseId")String baseId) throws Exception;

	public List<MassifProductbean> getBaseAndMassif(Map<String,Object> paramMap);
	
	public List<MassifProductbean> getBase(Map<String, Object> paramMap);
	
	public List<MassifProductbean> getMassif(@Param("baseId")String massifId);
	
	public  MassifProductbean getMassifAndDeviceListById(String id) throws Exception;
	
	public List<MassifProductbean> getBaseAndMassifList(Map<String, Object> mapParam) throws Exception;
	
	public List<MassifProductbean> CountDeviceNumber(String enterpriseId)throws Exception;

	public MassifProductbean getMassifAndProbeDataByDeviceSer(@Param("deviceId")String deviceId)throws Exception;
	public MassifProductbean updateMP(MassifProductbean massifProductbean)throws Exception;

	public void deleteMPByDeviceSer(String string) throws Exception;

	public void updateMassifProductByBaseId(Map<String, String> map)throws Exception;

	public void updateMassifProductByMassifId(Map<String, String> map)throws Exception;
}
