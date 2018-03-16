package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.beans.MassifProductbean;

public interface IMassIfProductService {
	/**
	 * 更新base
	 * @param massifProductbean
	 * @throws Exception
	 */
	public void updataMassifProduct(MassifProductbean massifProductbean) throws Exception;
	/**
	 * 更新massif
	 * @param massifProductbean
	 * @throws Exception
	 */
	public void updateMassifMass(MassifProductbean massifProductbean) throws Exception;
	/**
	 * 插入
	 * @param massifProductbean
	 * @throws Exception
	 */
	
	public void addMassifProduct(MassifProductbean massifProductbean) throws Exception;
	/**
	 * 根据基地名字找基地
	 * @param baseName
	 * @return
	 * @throws Exception
	 */
	public List<MassifProductbean> findByBaseName(String baseName) throws Exception;

	public List<MassifProductbean> getMassifAndProbeData(Map<String,String> paramMap);	
	
	/**
	 * 动态获取基地及相关设备信息信息
	 * @param paramMap 动态参数
	 * @return
	 * @throws Exception
	 * @author zmd
	 */
	public List<MassifProductbean> getMassifAndDeviceList(Map<String,Object> paramMap) throws Exception;	
	
	
	/**
	 * 获取设备信息信息
	 * @param paramMap 动态参数
	 * @return 设备集合
	 * @throws Exception
	 * @author 眯眯眼
	 */
	public List<MassifProductbean> getDeviceListForMassif(Map<String,Object> paramMap) throws Exception;
	

	public void updateMassifBaseEnter(Map<String, String> map) throws Exception;

	/**
	 * 根据企业ID获取基地信息
	 * @param enetrpriseId 企业ID
	 * @return
	 * @throws Exception
	 * @author zmd
	 */
	public List<MassifProductbean> selectMassifProductByEnterpriseId(String enetrpriseId) throws Exception;
	
	/**
	 * 根据企业ID以及基地ID获取基地信息
	 * @param enetrpriseId 企业ID
	 * @param baseId 基地ID
	 * @return
	 * @throws Exception
	 * @author zmd
	 */
	public List<MassifProductbean> selectMassifProductByEnterpriseIdAndBaseId(String enterpriseId,String baseId) throws Exception;
	
	/**
	 * 得到全部基地 还可以根据基地ID查找地块
	 * @param paramMap 
	 * @return
	 */
	public List<MassifProductbean> getBaseAndMassif(Map<String,Object> paramMap);
	
	public List<MassifProductbean> getBase(Map<String,Object> paramMap);
	
	public List<MassifProductbean> getMassif(String massifId);
	
	/**
	 * 通过ID查找地块信息
	 * @param id 地块表主键
	 * @return
	 * @throws Exception
	 *@author zmd
	 */
	public  MassifProductbean getMassifAndDeviceListById(String id) throws Exception;
	
	public  List<MassifProductbean> getBaseAndMassifList(Map<String, Object> mapParam) throws Exception;
	
	/**
	 * 通过企业ID查找地块关联表数据
	 * @param enterpriseId 企业ID
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public List<MassifProductbean> CountDeviceNumber(String enterpriseId)throws Exception;
	/**
	 * 修改基地时 同步修改绑定数据
	 * @param map
	 * @throws Exception
	 */
	public void updateMassifProductByBaseId(Map<String, String> map)throws Exception;
	/**
	 * 修改地块时同步修改绑定数据
	 * @param map
	 * @throws Exception
	 */
	public void updateMassifProductByMassifId(Map<String, String> map)throws Exception;
	
}
