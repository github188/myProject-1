package com.ssm.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.ProbeSpecificDataBackUpBean;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.beans.ProbeSpecificDataMonthBean;
import com.ssm.service.IProbeSpecificDataBackUpService;
import com.ssm.service.IProbeSpecificDataDayService;
import com.ssm.service.IProbeSpecificDataHourService;
import com.ssm.service.IProbeSpecificDataMonthService;
import com.ssm.service.IProbeSpecificDataService;
import com.ssm.service.impl.ProbeSpecificDataDayServiceImpl;
import com.ssm.service.impl.ProbeSpecificDataHourServiceImpl;
import com.ssm.service.impl.ProbeSpecificDataMonthServiceImpl;
import com.ssm.util.BaseUtil;
import com.ssm.util.StringUtils;


@Controller("apiBatchProcessAction")
public class BatchProcessAction extends BaseAction {
	
	@Resource
	private IProbeSpecificDataService probeSpecificDataService;
	@Resource
	private IProbeSpecificDataHourService probeSpecificDataHourService;
	@Resource
	private IProbeSpecificDataDayService probeSpecificDataDayService;
	@Resource
	private IProbeSpecificDataMonthService probeSpecificDataMonthService;
	@Resource
	private IProbeSpecificDataBackUpService probeSpecificDataBackUpService;
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	/**
	 * 每隔一个小时进行一次批处理
	 */
	public void BatchProcessForHour(){
		log.info("-----------小时批处理接口开始-------【apiBatchProcessAction_BatchProcessForHour.do】");
		try{
			Calendar cal = Calendar.getInstance();
	        Calendar newcal = Calendar.getInstance();
	        int year=cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH);
	        int day = cal.get(Calendar.DATE);
	        int oldhour = cal.get(Calendar.HOUR_OF_DAY)-1;//小时减1
	        int newhour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE);
	        int second = cal.get(Calendar.SECOND);
	        cal.set(year, month, day, oldhour, minute, second);
	        newcal.set(year, month, day, newhour, minute, second);
	        String oldstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime()); //当前时间前一个小时
	        String newstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(newcal.getTime()); //当前时间
	        ValueOperations<String, Object>  hash = getRedisTemplate().opsForValue();
			String hourTime=(String) hash.get("HourTime");//上次天统计时间
			HashMap<Object,Object> params=new HashMap<Object,Object>();
			//-1表示redis中的初始时间，若初始时间不为-1，则拿redis中的时间作为开始时间
			if(!hourTime.equals("-1")){
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =sdf.parse(hourTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR_OF_DAY, +1);//redis天统计时间+1，表示这次统计开始时间
				//拿redis时间和当前时间轴进行比较，如果比较值<0，则之前数据统计异常，将之前数据重新统计，比较值=0表示数据统计正常，则统计当日数据
				while((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()).compareTo(oldstr)<=0){
					params.put("startTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					calendar.add(Calendar.HOUR_OF_DAY, +1);//redis天统计时间+1，表示这次统计结束时间
					params.put("endTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					//根据开始时间和结束时间查询
					List<ProbeSpecificDataBean> list=probeSpecificDataHourService.AvgProbeSpecificDataByHour(params);
					List<ProbeSpecificDataHourBean> list1=new ArrayList<ProbeSpecificDataHourBean>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							ProbeSpecificDataHourBean probeSpecificDataHourBean=new ProbeSpecificDataHourBean();
							probeSpecificDataHourBean.setPkProbeId(list.get(i).getPkProbeId());
							probeSpecificDataHourBean.setPkDevicesId(list.get(i).getPkDevicesId());
							probeSpecificDataHourBean.setProbeData(list.get(i).getProbeData());
							probeSpecificDataHourBean.setProbeUnit(list.get(i).getProbeUnit());
							probeSpecificDataHourBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
							probeSpecificDataHourBean.setSurveyNo(list.get(i).getSurveyNo());
							list1.add(probeSpecificDataHourBean);
						}
						//批量插入数据到小时统计表中
						probeSpecificDataHourService.addProbeSpecificDataHour(list1);
						//将统计时间存入redis，用于判断是否执行成功并作为比较依据
						hash.set("HourTime",oldstr);
						log.info("小时批处理成功");
					}else{
						hash.set("HourTime",oldstr);
						log.info("该时段没有数据");
					}
				}
			}else{
				//初始化则按照系统时间获取开始时间
				params.put("startTime", oldstr);
				params.put("endTime", newstr);
				//根据开始时间和结束时间查询
				List<ProbeSpecificDataBean> list=probeSpecificDataHourService.AvgProbeSpecificDataByHour(params);
				List<ProbeSpecificDataHourBean> list1=new ArrayList<ProbeSpecificDataHourBean>();
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						ProbeSpecificDataHourBean probeSpecificDataHourBean=new ProbeSpecificDataHourBean();
						probeSpecificDataHourBean.setPkProbeId(list.get(i).getPkProbeId());
						probeSpecificDataHourBean.setPkDevicesId(list.get(i).getPkDevicesId());
						probeSpecificDataHourBean.setProbeData(list.get(i).getProbeData());
						probeSpecificDataHourBean.setProbeUnit(list.get(i).getProbeUnit());
						probeSpecificDataHourBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						probeSpecificDataHourBean.setSurveyNo(list.get(i).getSurveyNo());
						list1.add(probeSpecificDataHourBean);
					}
					//批量插入数据到小时统计表中
					probeSpecificDataHourService.addProbeSpecificDataHour(list1);
					//将统计时间存入redis，用于判断是否执行成功并作为比较依据
					hash.set("HourTime",oldstr);
					log.info("小时批处理成功");
				}else{
					hash.set("HourTime",oldstr);
					log.info("该时段没有数据");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("小时批处理发生异常："+e.getMessage(),e);
		}
		log.info("-----------小时批处理接口结束--------------");
	}

	/**
	 * 每天进行一次批处理
	 */
	public void BatchProcessForDay(){
		log.info("-----------天批处理接口开始---------【apiBatchProcessAction_BatchProcessForDay.do】");
		try{
			Calendar cal = Calendar.getInstance();
	        Calendar newcal = Calendar.getInstance();
	        Calendar backcal = Calendar.getInstance();
	        int year=cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH);
	        int oldday = cal.get(Calendar.DATE)-1;//天数减1,用于数据统计
	        int backday = cal.get(Calendar.DATE)-2;//天数减2,用于数据备份
	        int newday = cal.get(Calendar.DATE);//当前时间
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE);
	        int second = cal.get(Calendar.SECOND);
	        backcal.set(year, month, backday, hour, minute, second);
	        cal.set(year, month, oldday, hour, minute, second);
	        newcal.set(year, month, newday, hour, minute, second);
	        String backstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(backcal.getTime()); //当前时间两天前
	        String oldstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime()); //当前时间一天前
	        String newstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(newcal.getTime()); //当前时间
			HashMap<Object,Object> params=new HashMap<Object,Object>();
			ValueOperations<String, Object>  hash = getRedisTemplate().opsForValue();
			String backTime=(String) hash.get("backUpTime");//上次备份时间
			String dayTime=(String) hash.get("DayTime");//上次天统计时间
			//-1表示redis中的初始时间，若初始时间不为-1，则拿redis中的时间作为开始时间
				if(!backTime.equals("-1")){
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date =sdf.parse(backTime);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DATE, +1); //redis备份时间+1，表示这次备份时间
					params.put("backStartTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					params.put("backEndTime", oldstr);
				}else{
					//初始化则按照系统时间获取开始时间
					params.put("backStartTime", backstr);
					params.put("backEndTime", oldstr);
				}
			//天统计批处理
			if(!dayTime.equals("-1")){
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =sdf.parse(dayTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, +1);//redis天统计时间+1，表示这次统计时间
				//拿redis时间和当前时间轴进行比较，如果比较值<0，则之前数据统计异常，将之前数据重新统计，比较值=0表示数据统计正常，则统计当日数据
				while((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()).compareTo(oldstr)<=0){
					params.put("startTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					calendar.add(Calendar.DATE, +1);
					params.put("endTime",  (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					List<ProbeSpecificDataHourBean> list=probeSpecificDataHourService.AvgProbeSpecificDataByDay(params);
					List<ProbeSpecificDataDayBean> list1=new ArrayList<ProbeSpecificDataDayBean>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							ProbeSpecificDataDayBean probeSpecificDataDayBean=new ProbeSpecificDataDayBean();
							probeSpecificDataDayBean.setPkProbeId(list.get(i).getPkProbeId());
							probeSpecificDataDayBean.setPkDevicesId(list.get(i).getPkDevicesId());
							probeSpecificDataDayBean.setProbeData(list.get(i).getProbeData());
							probeSpecificDataDayBean.setProbeUnit(list.get(i).getProbeUnit());
							probeSpecificDataDayBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
							probeSpecificDataDayBean.setSurveyNo(list.get(i).getSurveyNo());
							list1.add(probeSpecificDataDayBean);
						}
						//批量插入数据到天统计表中
						probeSpecificDataDayService.addProbeSpecificDataDay(list1);
						//将统计时间存入redis，用于判断是否执行成功并作为比较依据
						hash.set("DayTime",oldstr);
						log.info("天批处理成功");
					}else{
						hash.set("DayTime",oldstr);
						log.info("该天内没有数据");
					}
				}
			}else{
				//redis初始化值为-1，则拿当前时间
				params.put("startTime", oldstr);
				params.put("endTime", newstr);
				List<ProbeSpecificDataHourBean> list=probeSpecificDataHourService.AvgProbeSpecificDataByDay(params);
				List<ProbeSpecificDataDayBean> list1=new ArrayList<ProbeSpecificDataDayBean>();
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						ProbeSpecificDataDayBean probeSpecificDataDayBean=new ProbeSpecificDataDayBean();
						probeSpecificDataDayBean.setPkProbeId(list.get(i).getPkProbeId());
						probeSpecificDataDayBean.setPkDevicesId(list.get(i).getPkDevicesId());
						probeSpecificDataDayBean.setProbeData(list.get(i).getProbeData());
						probeSpecificDataDayBean.setProbeUnit(list.get(i).getProbeUnit());
						probeSpecificDataDayBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						probeSpecificDataDayBean.setSurveyNo(list.get(i).getSurveyNo());
						list1.add(probeSpecificDataDayBean);
					}
					//批量插入数据到天统计表中
					probeSpecificDataDayService.addProbeSpecificDataDay(list1);
					//将统计时间存入redis，用于判断是否执行成功并作为比较依据
					hash.set("DayTime",oldstr);
					log.info("天批处理成功");
				}else{
					hash.set("DayTime",oldstr);
					log.info("该天内没有数据");
				}
			}
				//批量插入数据到数据备份表中
				probeSpecificDataBackUpService.BackUpProbeSpecificData(params);
				//数据备份成功将备份时间存入redis中用于下次执行判断
				hash.set("backUpTime",backstr);
				log.info("数据备份成功");
		}catch(Exception e){
			e.printStackTrace();
			log.error("天批处理发生异常："+e.getMessage(),e);
		}
	       log.info("-----------天批处理接口结束--------------");
	}
	
	/**
	 * 每隔一个月进行一次批处理
	 */
	public void BatchProcessForMonth(){
		log.info("-----------月批处理接口开始---------【apiBatchProcessAction_BatchProcessForMonth.do】");
		try{
			Calendar cal = Calendar.getInstance();
	        Calendar newcal = Calendar.getInstance();
	        int year=cal.get(Calendar.YEAR);
	        int oldmonth = cal.get(Calendar.MONTH)-1;//月数减1
	        int newmonth = cal.get(Calendar.MONTH);
	        int day = cal.get(Calendar.DATE);
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE);
	        int second = cal.get(Calendar.SECOND);
	        cal.set(year, oldmonth, day, hour, minute, second);
	        newcal.set(year, newmonth, day, hour, minute, second);
	        String oldstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime()); //当前时间前一个月
	        String newstr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(newcal.getTime()); //当前时间
	        ValueOperations<String, Object>  hash = getRedisTemplate().opsForValue();
			String hourTime=(String) hash.get("HourTime");//上次天统计时间
			HashMap<Object,Object> params=new HashMap<Object,Object>();
			//-1表示redis中的初始时间，若初始时间不为-1，则拿redis中的时间作为开始时间
			if(!hourTime.equals("-1")){
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =sdf.parse(hourTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, +1);//redis天统计时间+1，表示这次统计时间
				//拿redis时间和当前时间轴进行比较，如果比较值<0，则之前数据统计异常，将之前数据重新统计，比较值=0表示数据统计正常，则统计当日数据
				while((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()).compareTo(oldstr)<=0){
					params.put("startTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					calendar.add(Calendar.MONTH, +1);
					params.put("endTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime()));
					//通过开始时间和结束时间进行查询
					List<ProbeSpecificDataDayBean> list= probeSpecificDataDayService.AvgProbeSpecificDartaByMonth(params);
					List<ProbeSpecificDataMonthBean> list1= new ArrayList<ProbeSpecificDataMonthBean>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							ProbeSpecificDataMonthBean probeSpecificDataMonthBean=new ProbeSpecificDataMonthBean();
							probeSpecificDataMonthBean.setPkProbeId(list.get(i).getPkProbeId());
							probeSpecificDataMonthBean.setPkDevicesId(list.get(i).getPkDevicesId());
							probeSpecificDataMonthBean.setProbeData(list.get(i).getProbeData());
							probeSpecificDataMonthBean.setProbeUnit(list.get(i).getProbeUnit());
							probeSpecificDataMonthBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							probeSpecificDataMonthBean.setSurveyNo(list.get(i).getSurveyNo());
							list1.add(probeSpecificDataMonthBean);
						}
						//批量插入数据到月统计数据表中
						probeSpecificDataMonthService.addProbeSpecificDataMonth(list1);
						hash.set("MonthTime",oldstr);
						log.info("月批处理成功");
					}else{
						hash.set("MonthTime",oldstr);
						log.info("该月内没有数据");
					}	
				 }
				}else{
					params.put("startTime", oldstr);
					params.put("endTime", newstr);
					//通过开始时间和结束时间进行查询
					List<ProbeSpecificDataDayBean> list= probeSpecificDataDayService.AvgProbeSpecificDartaByMonth(params);
					List<ProbeSpecificDataMonthBean> list1= new ArrayList<ProbeSpecificDataMonthBean>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							ProbeSpecificDataMonthBean probeSpecificDataMonthBean=new ProbeSpecificDataMonthBean();
							probeSpecificDataMonthBean.setPkProbeId(list.get(i).getPkProbeId());
							probeSpecificDataMonthBean.setPkDevicesId(list.get(i).getPkDevicesId());
							probeSpecificDataMonthBean.setProbeData(list.get(i).getProbeData());
							probeSpecificDataMonthBean.setProbeUnit(list.get(i).getProbeUnit());
							probeSpecificDataMonthBean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							probeSpecificDataMonthBean.setSurveyNo(list.get(i).getSurveyNo());
							list1.add(probeSpecificDataMonthBean);
						}
						//批量插入数据到月统计数据表中
						probeSpecificDataMonthService.addProbeSpecificDataMonth(list1);
						hash.set("MonthTime",oldstr);
						log.info("月批处理成功");
					}else{
						hash.set("MonthTime",oldstr);
						log.info("该月内没有数据");
					}	
				}
		}catch(Exception e){
			e.printStackTrace();
			log.error("月批处理发生异常："+e.getMessage(),e);
		}
		log.info("-----------月批处理接口结束--------------");
	}
}
