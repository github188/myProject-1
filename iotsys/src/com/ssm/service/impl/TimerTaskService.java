package com.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.mail.internet.NewsAddress;
import javax.management.loading.PrivateClassLoader;

import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP.Connection.Start;
import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.service.IPowerControllerService;
import com.ssm.service.ITimerTaskService;

public class TimerTaskService implements ITimerTaskService {

	private Date startDate;    //开始执行的日期
	private Date endDate;      //通知结束的日期
	private long perDaySecond=1000*60*60*24;  //1天的秒数
	private long intravlDays;   //间隔天数
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource
	private IPowerControllerService PowerControllerService;
	
	
	@Override
	public boolean doTask(final TimerControllerInfoBean timerControllerInfoBean) throws Exception {

		//每天的这一时刻开始执行任务
		final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"+ " '"+timerControllerInfoBean.getControlPeriodStart()+"'");
		startDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
		
		intravlDays=perDaySecond*timerControllerInfoBean.getIntervalDays();  // 间隔的天数
		Timer timer=new Timer(); //定时控制器
		TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//到时间点自动开启控制器,修改数据库中控制器switch属性
				Map<String, String> paramMap=new HashMap<String, String>();
				paramMap.put("id",timerControllerInfoBean.getControllerId());
				paramMap.put("powerswitch","3");   //控制器开关。 3是开   0是关
				paramMap.put("updatetime",df.format(new Date()));
				try {
					//开启控制器
					PowerControllerService.controllerTurnOnOff(paramMap);
					log.info("=================控制器开启成功====================");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("=================控制器开启成功====================");
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		};
		
		try {
			//执行定时任务
			timer.scheduleAtFixedRate(timerTask, startDate, intravlDays); //任务 ， 第一次开始执行时间  , 间隔
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

	@Override
	public boolean stopTask() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
