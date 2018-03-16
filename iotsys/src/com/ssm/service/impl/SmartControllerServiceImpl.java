package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ParameterControllerInfoBean;
import com.ssm.beans.SmartControllerBean;
import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.mapper.ParameterControllerInfoMapper;
import com.ssm.mapper.SmartControllerMapper;
import com.ssm.mapper.TimerControllerInfoMapper;
import com.ssm.service.ISmartControllerService;

@Service("smartControllerService")
public class SmartControllerServiceImpl implements ISmartControllerService {

	@Resource
	private SmartControllerMapper SmartControllerDao;
	
	@Resource
	private ParameterControllerInfoMapper parameterControllerInfoMapper;
	
	@Resource
	private TimerControllerInfoMapper timerControllerInfoMapper;
	
	@Override
	public List<SmartControllerBean> selectAll() throws Exception {
		// TODO Auto-generated method stub
		
		return SmartControllerDao.selectAll();
	}
	

	@Override
	public void addParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean) throws Exception {
		parameterControllerInfoMapper.addParameterControllerInfo(parameterControllerInfoBean);
	}

	@Override
	public void updateParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean)
			throws Exception {
		parameterControllerInfoMapper.updateParameterControllerInfo(parameterControllerInfoBean);
	}

	@Override
	public void deleteParameterControllerInfoById(String id) throws Exception {
		parameterControllerInfoMapper.deleteParameterControllerInfoById(id);
	}
	
	@Override
	public void addTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean) throws Exception {
		timerControllerInfoMapper.addTimerControllerInfo(timerControllerInfoBean);
	}

	@Override
	public void updateTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean) throws Exception {
		timerControllerInfoMapper.updateTimerControllerInfo(timerControllerInfoBean);
	}

	@Override
	public void deleteTimerControllerInfoById(String id) throws Exception {
		timerControllerInfoMapper.deleteTimerControllerInfoById(id);
	}

}
