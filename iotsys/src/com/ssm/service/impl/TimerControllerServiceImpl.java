package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.mapper.TimerControllerInfoMapper;
import com.ssm.service.ITimerControllerService;


@Service("timerControllerService")
@Scope("prototype")
public class TimerControllerServiceImpl implements ITimerControllerService {

	@Resource 
	private TimerControllerInfoMapper timerControllerDao;
	


	
	@Override
	public List<TimerControllerInfoBean> selectByOutKey() throws Exception {
		// TODO Auto-generated method stub
		return timerControllerDao.selectByOutKey();
	}
	
//	增
	@Override
	public void addTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean) throws Exception {
		// TODO Auto-generated method stub
		timerControllerDao.addTimerControllerInfo(timerControllerInfoBean);

	}
	
//	改
	@Override
	public void updateTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean) throws Exception {
		// TODO Auto-generated method stub
		timerControllerDao.updateTimerControllerInfo(timerControllerInfoBean);
	}
	
//	删
	@Override
	public void deleteTimerControllerInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		timerControllerDao.deleteTimerControllerInfoById(id);
	}

	@Override
	public TimerControllerInfoBean getTimerControllerInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return timerControllerDao.getTimerControllerInfoById(id);
	}

}
