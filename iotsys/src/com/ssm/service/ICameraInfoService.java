package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.beans.CameraInfoBean;

public interface ICameraInfoService {
public void addCamera(CameraInfoBean cameraInfoBean) throws Exception;
	
	public void updateCamera(CameraInfoBean cameraInfoBean) throws Exception;
	
	public void deleteCaneraById( String id) throws Exception;
	
	public List<CameraInfoBean> selectAll(Map<String, String> map) throws Exception;
	
	public CameraInfoBean getById(String id) throws Exception;
	
	public List<CameraInfoBean> findByEnterprId(String enterprId) throws Exception;

	public CameraInfoBean findByChannelAndpk(String channel, String pkDeviceId) throws Exception;
	
	public List<CameraInfoBean> findBySer(Map<String, String> params) throws Exception;
}
