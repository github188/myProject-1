package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ParameterControllerInfoBean;
import com.ssm.mapper.ParameterControllerInfoMapper;
import com.ssm.service.IParameterControllerService;

@Service("parameterControllerService")
public class ParameterControllerServiceImpl implements IParameterControllerService {

	@Resource
	private ParameterControllerInfoMapper ParameterControllerInfoDao;
	

	@Override
	public List<ParameterControllerInfoBean> selectByOutKey() throws Exception {
		// TODO Auto-generated method stub
		return ParameterControllerInfoDao.selectByOutKey();
	}

	@Override
	public void addParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean) throws Exception {
		// TODO Auto-generated method stub
		ParameterControllerInfoDao.addParameterControllerInfo(parameterControllerInfoBean);
	}

	@Override
	public void updateParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean)
			throws Exception {
		// TODO Auto-generated method stub
		ParameterControllerInfoDao.updateParameterControllerInfo(parameterControllerInfoBean);
	}

	@Override
	public void deleteParameterControllerInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		ParameterControllerInfoDao.deleteParameterControllerInfoById(id);
	}

	@Override
	public ParameterControllerInfoBean getParameterControllerInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return ParameterControllerInfoDao.getParameterControllerInfoById(id);
	}


}
