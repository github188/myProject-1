package com.ssm.service;

import com.rabbitmq.client.AMQP.Channel.Open;
import com.ssm.beans.TimerControllerInfoBean;

public interface ITimerTaskService {

	//开启任务
	public boolean doTask(TimerControllerInfoBean timerControllerInfoBean) throws Exception;
	
	//停止任务
	public boolean stopTask() throws Exception;
}
