package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.CameraInfoBean;
import com.ssm.mapper.CameraInfoMapper;
import com.ssm.service.ICameraInfoService;

@Service("cameraService")
public class CameraServiceImpl implements ICameraInfoService {
	@Resource
	private CameraInfoMapper cameraDao;
	
	/**
	 * 
	 */
	@Override
	public void addCamera(CameraInfoBean cameraInfoBean) throws Exception {
		cameraDao.addCamera(cameraInfoBean);
	}

	@Override
	public void updateCamera(CameraInfoBean cameraInfoBean) throws Exception {
		cameraDao.updateCamera(cameraInfoBean);
	}

	@Override
	public void deleteCaneraById(String id) throws Exception {
		cameraDao.deleteCaneraById(id);
	}

	@Override
	public List<CameraInfoBean> selectAll(Map<String, String> map) throws Exception {
		List<CameraInfoBean> list = cameraDao.selectAll(map);
		if (null!=list&&list.size()>0) {
			return list;
		}
		return null;
	}

	@Override
	public CameraInfoBean getById(String id) throws Exception {
		return cameraDao.queryById(id);
	}

	@Override
	public List<CameraInfoBean> findByEnterprId(String enterprId) throws Exception {
		// TODO Auto-generated method stub
		return  cameraDao.findByEnterprId(enterprId);
	}

	@Override
	public CameraInfoBean findByChannelAndpk(String channel, String pkDeviceId)
			throws Exception {
		// TODO Auto-generated method stub
		return cameraDao.findByChannelAndpk(channel,pkDeviceId);
	}

	@Override
	public List<CameraInfoBean> findBySer(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		return cameraDao.findBySer(params);
	}

}
