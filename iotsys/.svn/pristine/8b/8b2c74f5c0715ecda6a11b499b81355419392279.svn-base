package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.CommonBean;
import com.ssm.beans.ProbeBean;

public interface ProbeMapper {

	
	public List<ProbeBean> findAll() throws Exception;

	public List<ProbeBean> getProbeAndProbeData(Map<String, String> params) throws Exception;

	public void insertMany(@Param("probeBeans")List<ProbeBean> probeBeans) throws Exception;

	public List<ProbeBean> findByDeviceSerialNumber(String serialNumber) throws Exception;
	
	public void updateBatch(List<ProbeBean> list)throws Exception;
	
	public void updateProbe(ProbeBean probeBeans)throws Exception;
	
	public 	CommonBean  getSpecificData(Map<String,String> probeBeans)throws Exception;
	
	public 	CommonBean  getSpecificDataCountForPie(Map<String,String> probeBeans)throws Exception;

	public void deleteByDeviceSer(@Param("serialNumber")String serialNumber) throws Exception;
	
	public void deleteByIds(@Param("ids")List<String> ids) throws Exception;
}
