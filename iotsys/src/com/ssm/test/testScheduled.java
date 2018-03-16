package com.ssm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Component;

@Component

public class testScheduled {
	
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;  //每天的秒数

	public static void main(String[] args) throws ParseException 
	{
	
//		Calendar calendar=Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, 14);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
//		Date time=df.parse("2018-3-16 16:06:35");
//		
//		Timer timer=new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//		
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				timeTask();
//			}
//		}, time, 1000 *5);
		
		
		// 一天的毫秒数
		 long daySpan = 24 * 60 * 60 * 1000;
		  
		 // 规定的每天时间15:33:30运行
		 final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '17:02:30'");
		 // 首次运行时间
		 Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
		  
		 // 如果今天的已经过了 首次运行时间就改为明天
		 if(System.currentTimeMillis() > startTime.getTime())
		 {
			 startTime = new Date(startTime.getTime() + daySpan);
			 System.out.println("时间已过期，任务将于明天执行");
		 }
		 
		  
		 Timer t = new Timer();
		 System.out.println("============程序已经运行=============");
		 System.out.println(startTime);
		 TimerTask task = new TimerTask(){
		  @Override
		  public void run() {
		   // 要执行的代码
		   System.out.println(new Date());
		  }
		 };
		 
		 // 以每24小时执行一次
		 t.scheduleAtFixedRate(task, startTime, 5 * 1000);
	}
	
	public static void timeTask()
	{
		System.out.println("===============定时任务执行中================");
		System.out.println(new Date());  
	}
	
	
 
}
