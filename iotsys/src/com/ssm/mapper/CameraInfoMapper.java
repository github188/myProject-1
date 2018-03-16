package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.CameraInfoBean;

public interface CameraInfoMapper {
	public void addCamera(CameraInfoBean cameraInfoBean) throws Exception;
	
	public void updateCamera(CameraInfoBean cameraInfoBean) throws Exception;
	
	public void deleteCaneraById(@Param("id") String id) throws Exception;
	
	public List<CameraInfoBean> selectAll(Map<String, String> map) throws Exception;
	
	public CameraInfoBean queryById(@Param("id")String id) throws Exception;

	public List<CameraInfoBean> findByEnterprId(String enterprId) throws Exception;
	
	public List<CameraInfoBean> findBySer(Map<String, String> params) throws Exception;

	public CameraInfoBean findByChannelAndpk(@Param("channel")String channel, @Param("pkDeviceId")String pkDeviceId)throws Exception;
}
