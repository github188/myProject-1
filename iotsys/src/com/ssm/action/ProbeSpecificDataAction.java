package com.ssm.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.CommonBean;
import com.ssm.beans.PowerControllerBean;
import com.ssm.beans.ProbeBean;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.service.IPowerControllerService;
import com.ssm.service.IProbeService;
import com.ssm.service.IProbeSpecificDataService;
import com.ssm.util.BaseUtil;
import com.ssm.util.StringUtils;

@Controller("probeSpecificDataAction")
public class ProbeSpecificDataAction extends BaseAction {
	@Resource
	private IProbeSpecificDataService dataService;
	@Resource
	private IProbeService probeService;
	@Resource
	private IPowerControllerService powerControllerService;
	
	private Logger log = Logger.getLogger(this.getClass());		
	
	private String deviceId;    
	private String startTime;
	private String endTime;
	private String powterSwitch;
	private String controllerId;
	private String cnm;
	
	
	/**
	 * 获取各种探头的统计
	 */
	public void getSpecificDataCount(){
		log.info("开始根据设备号码找到所有探头的数据统计deviceId："+deviceId);
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
//		String flag="";
//		if (null!=startTime && null != endTime &&!startTime.equals("") && !startTime.equals("")) {
//			int a=Integer.parseInt(endTime.replace("-", ""))-Integer.parseInt(startTime.replace("-", ""));
//			if (a==0) {
//				flag="0";
//			}else if(a<30){
//				flag="1";
//			}else {
//				flag="2";
//			}
//		}
		//获取设备的探头
		List<ProbeBean> list = probeService.findByDeviceSerialNumber(deviceId);
		if (list==null||list.size()<=0) {
			domain.setCode("003");
			domain.setMsg("没有数据");
			return;
		}
		List<CommonBean> dataBeans=new ArrayList<>();
		DecimalFormat dfn = new DecimalFormat("###,##0.0");
		//循环获取探头的数据
		for (ProbeBean probeBean : list) {
			Map<String, Object> map=new HashMap<>();
			 
			map.put("deviceId", probeBean.getDeviceSerialNumber());
			map.put("surveyNo", probeBean.getSurveyNo());
			Date d=new Date();   
			SimpleDateFormat  df=new SimpleDateFormat("yyyy-MM-dd 00:00:00");   
			if(cnm.equals("0")){
				df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				startTime = df.format(new Date(d.getTime() - (long)8 * 60 * 60 * 1000));  //前8个小时
				endTime =df.format(new Date(d.getTime()));
			}else if(cnm.equals("1")){
				startTime = df.format(new Date(d.getTime() - (long)7 * 24 * 60 * 60 * 1000));//一周内
				map.put("tableNames","iot_probe_specific_data_day");
			}else if(cnm.equals("2")){ 
				startTime = df.format(new Date(d.getTime() - (long)30 * 24 * 60 * 60 * 1000));//一个月内
				map.put("tableNames","iot_probe_specific_data_day");
			}else if(cnm.equals("3")){
				startTime = df.format(new Date(d.getTime() - (long)180 * 24 * 60 * 60 * 1000));//6个月内
				map.put("tableNames","iot_probe_specific_data_month");
			}else if(cnm.equals("-1")){
				startTime = startTime+" 00:00:00";
				df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				endTime =df.format(new Date(d.getTime()));
			} 
			map.put("stratTime", startTime);
			map.put("endTime", endTime);
			List<ProbeSpecificDataBean> dataCount=null;
			if (cnm.equals("0")) { //查询原始数据表8个小时内的数据
				dataCount = dataService.queryEnvirDataCount(map);
			}else if (cnm.equals("1")|| cnm.equals("2")) {//查询原始数据天统计表1周内的数据  //查询原始数据天统计表1月内的数据
				dataCount = dataService.queryEnvirDataCountDay(map);
			}else if(cnm.equals("-1") || cnm.equals("3")){//查询原始数据月统计表6个月内的数据  //查询原始数据月统计表(自定的时间按月查)
				dataCount = dataService.queryEnvirDataCountMonth(map);
			}
			CommonBean bean = new CommonBean();
			ArrayList<Object> list2 = new ArrayList<>();
			ArrayList<Object> list3=new ArrayList<>();
			ArrayList<Object> listMax=new ArrayList<>();
			ArrayList<Object> listMin=new ArrayList<>();
			if (null!=dataCount) {
				log.info("探头数据不为空开始循环整理探头数据--------------------------");
				for (ProbeSpecificDataBean dataBean : dataCount) {
					if (null!=dataBean) {
						String time="";
						if (cnm.equals("0")) {
						  time = dataBean.getCreateTime().substring(11, 13)+":00";
						}else if (cnm.equals("1")|| cnm.equals("2")) {
							time = dataBean.getCreateTime().substring(8, 10);
						}else {
							time = dataBean.getCreateTime().substring(6, 7);
						}
						list2.add(time);
						Float probeData = dataBean.getProbeData();
						
						Float avgAll = Float.parseFloat(probeData+"");
						String avgA= dfn.format(avgAll);
						list3.add(avgA);
						
						listMax.add(probeBean.getSurveyMax());
						listMin.add(probeBean.getSurveyMin());
					}
				}
				log.info("探头数据不为空结束循环整理探头数据--------------------------");
			}
			String avg = "0.0";
			if (cnm.equals("0")) {
					 avg = dataService.queryEnvirDataAvg(map);
				}else if (cnm.equals("1") ||cnm.equals("2")) {
					 avg = dataService.queryEnvirDataDayAvg(map);
				}else {
					 avg = dataService.queryEnvirDataMonthAvg(map);
				}
			bean.setT1(probeBean.getSurveyName());
			if(avg==null || avg=="null" || avg=="null" || avg.length()==0){
				avg = "0.0";
			}
			double s = Double.parseDouble(avg);
			String avgs= dfn.format(s);
			bean.setT2(avgs);
			bean.setT3(list2);
			bean.setT4(list3);
			bean.setT5(probeBean.getSurveyMax());
			bean.setT6(probeBean.getSurveyMin());
			bean.setT7(probeBean.getUnit());
			bean.setT9(listMin);
			bean.setT10(listMax);
			dataBeans.add(bean);
		}
		domain.setMsg("success");
		domain.setData(dataBeans);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error(e.toString());
			e.printStackTrace();
		}finally{
			try {
				log.info("结束根据设备号码找到所有探头的数据统计deviceId："+deviceId);
				this.printObject(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取各种探头的统计
	 */
	public void getSpecificDataCountForPie(){
		log.info("开始获取环境数据饼状图数据统计deviceId："+deviceId);
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			Map<String, String> paramMap=new HashMap<String, String>();
			paramMap.put("serialNumber", deviceId);
			paramMap.put("endTime", endTime+" 23:59:59");
			
			Date d=new Date();   
			SimpleDateFormat  df=new SimpleDateFormat("yyyy-MM-dd 00:00:00");   
			if(cnm.equals("0")){
				df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				startTime = df.format(new Date(d.getTime() - (long)8 * 60 * 60 * 1000));  //前8个小时
			}else if(cnm.equals("1")){
				startTime = df.format(new Date(d.getTime() - (long)7 * 24 * 60 * 60 * 1000));//一周内
				paramMap.put("tableNames","iot_probe_specific_data_day");
			}else if(cnm.equals("2")){ 
				startTime = df.format(new Date(d.getTime() - (long)30 * 24 * 60 * 60 * 1000));//一个月内
				paramMap.put("tableNames","iot_probe_specific_data_day");
			}else if(cnm.equals("3")){
				startTime = df.format(new Date(d.getTime() - (long)180 * 24 * 60 * 60 * 1000));//6个月内
				paramMap.put("tableNames","iot_probe_specific_data_month");
			}else if(cnm.equals("-1")){
				startTime = startTime+" 00:00:00";
				df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				paramMap.put("endTime",df.format(new Date(d.getTime())));
				paramMap.put("tableNames","iot_probe_specific_data_month"); //自定义的事件查月表
			} 
			paramMap.put("startTime", startTime);
			
			
		//获取设备的探头
		List<ProbeBean> probeList = probeService.findByDeviceSerialNumber(deviceId);
		
		for(int x=0;x<probeList.size();x++){
			ProbeBean probeBean = probeList.get(x);
			paramMap.put("surveyNo",probeList.get(x).getSurveyNo()+"");	
			paramMap.put("minNo",probeList.get(x).getSurveyMin());	
			paramMap.put("maxNo",probeList.get(x).getSurveyMax());	
			CommonBean commonBean =null;
			if(cnm.equals("0")){
				commonBean = probeService.getSpecificDataCountForPie(paramMap); //查询原始数据表   时
			}else if(cnm.equals("-1") || cnm.equals("3")){
				commonBean = probeService.getSpecificData(paramMap);//查询原始数据表统计表   时  天   月
			}
			probeBean.setCommonBean(commonBean);
			probeList.set(x, probeBean);
		}
		
		if (probeList==null||probeList.size()<=0) {
			domain.setCode("003");
			domain.setMsg("没有数据");
			log.info("结束获取环境数据饼状图数据统计deviceId："+deviceId+"  没有数据");
			return;
		}
		domain.setData(probeList);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			e.printStackTrace();
			log.info(e.toString());
		}finally{
			try {
				log.info("结束获取环境数据饼状图数据统计deviceId："+deviceId);
				this.printObject(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @author mmy
	 * @creatTime 2018年1月25日 下午4:42:14
	 */
	public void setControllerTurnOnOff(){
		log.info("开始设置控制器开关controllerId："+controllerId);
		
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		Map<String, String> paramMap=new HashMap<String, String>();
		paramMap.put("powerswitch", powterSwitch);
		paramMap.put("id", controllerId);
		  
		try {
		//获取设备的探头
		paramMap.put("updatetime", BaseUtil.getCurrentTimestr());
		
		powerControllerService.controllerTurnOnOff(paramMap);
		domain.setData(null);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}finally{
			try {
				log.info("结束设置控制器开关controllerId："+controllerId);
				this.printObject(domain);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.toString());
			}
		}
	}
	
	
	/**
	 * 获取各种探头的统计
	 * @author mmy
	 * @creatTime 2018年1月25日 上午11:56:58
	 */
	public void getPowerController(){
		log.info("开始根据设备序号得到设备所有的所有控制器deviceId："+deviceId);
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
		//获取设备的探头
		List<PowerControllerBean> list = powerControllerService.findPowerByDeviceSerialNumber(deviceId);
		domain.setData(list);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error(e.toString());
			e.printStackTrace();
		}finally{
			try {
				log.info("结束根据设备序号得到设备所有的所有控制器deviceId："+deviceId);
				this.printObject(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPowterSwitch() {
		return powterSwitch;
	}
	public void setPowterSwitch(String powterSwitch) {
		this.powterSwitch = powterSwitch;
	}
	public String getControllerId() {
		return controllerId;
	}
	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}
	public String getCnm() {
		return cnm;
	}

	public void setCnm(String cnm) {
		this.cnm = cnm;
	}


}
