package com.ssm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssm.beans.TimerControllerInfoBean;


public interface ITimerControllerService {
	
	//通过控制器ID选择相应的时间控制参数
	public List<TimerControllerInfoBean> selectByOutKey() throws Exception;
	
	//增加时间控制数据
	public void addTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean)  throws Exception;
	
	//更新时间控制数据
	public void updateTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean)  throws Exception;
	
	//删除时间控制数据(根据ID进行删除)
	public void deleteTimerControllerInfoById(String id)  throws Exception;
	
	public TimerControllerInfoBean getTimerControllerInfoById(String id) throws Exception;
}
