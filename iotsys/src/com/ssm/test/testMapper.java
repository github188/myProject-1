package com.ssm.test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.TimerService;
import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ssm.beans.Code;
import com.ssm.beans.DeviceInfoBean;
import com.ssm.beans.MassifProductbean;
import com.ssm.beans.ParameterControllerInfoBean;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.SmartControllerBean;
import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.service.ISmartControllerService;
import com.ssm.service.ITimerControllerService;
import com.ssm.service.impl.CodeServiceImpl;
import com.ssm.service.impl.DeviceInfoServiceImpl;
import com.ssm.service.impl.MassIfProductServiceImpl;
import com.ssm.service.impl.ParameterControllerServiceImpl;
import com.ssm.service.impl.ProbeServiceImpl;
import com.ssm.service.impl.ProbeSpecificDataServiceImpl;
import com.ssm.service.impl.SmartControllerServiceImpl;
import com.ssm.service.impl.TimerControllerServiceImpl;
import com.ssm.util.BaseUtil;

public class testMapper {
//
//	@Resource
//	static ISmartControllerService smartControllerService;
//
//	@Autowired
//	List<SmartControllerBean> smartControllerList;
	
	
	@Autowired
	 private ITimerControllerService timerService;
	
	
	public static void main(String[] args) {

		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			SmartControllerServiceImpl is = (SmartControllerServiceImpl) ctx.getBean("smartControllerService");
			System.out.println("===="+is.selectAll()); //这个可以运行
			
			
//			TimerControllerServiceImpl timerControllerServiceImpl=(TimerControllerServiceImpl)ctx.getBean("timerControllerService");
//			TimerControllerInfoBean timerControllerInfoBean=new TimerControllerInfoBean();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			String id="7c1b91ceeec04f6a81518a30970650c7";
//			String controllerId="b97aeeddd93556dd96afb3a611f7f952";
//			String deviceSerialNumber="C0180602211708090002";
//			Integer surveyNo=1;
			Integer intervalDays=777;
			String controlPeriodStart="08:50:20";
			String controlPeriodEnd="10:20:10";
			Boolean isRegular=true;
			String regularStartDate="2008-05-12";
			String regularEndDate="2008-05-12";
//			Boolean controllerStatus=true;
			
//			String createTime=df.format(new Date());
//			String updateTime=df.format(new Date());
			
			
//			timerControllerInfoBean.setId(id);
//			timerControllerInfoBean.setControllerId(controllerId);		
//			timerControllerInfoBean.setDeviceSerialNumber(deviceSerialNumber);
//			timerControllerInfoBean.setSurveyNo(surveyNo);
//			
//			timerControllerInfoBean.setIntervalDays(intervalDays);
//			
//			timerControllerInfoBean.setControlPeriodStart(controlPeriodStart);
//			
//			timerControllerInfoBean.setControlPeriodEnd(controlPeriodEnd);
//			
//			timerControllerInfoBean.setIsRegular(isRegular);
//			
//			timerControllerInfoBean.setRegularStartDate(regularStartDate);
//			
//			timerControllerInfoBean.setRegularEndDate(regularEndDate);
//			
//			timerControllerInfoBean.setControllerStatus(controllerStatus);
//			
//			timerControllerInfoBean.setCreateTime(createTime);
//			
//			timerControllerInfoBean.setUpdateTime(updateTime);
			
			//System.out.println("=================添加成功=======================");
//			System.out.println(timerControllerInfoBean);
			//添加Timer参数  就是这个
			//timerControllerServiceImpl.updateTimerControllerInfo(timerControllerInfoBean);
			
			
			//删除可用
//			timerControllerServiceImpl.deleteTimerControllerInfoById(id);
			
			
			
			
			String id="1";
			String controllerId="b97aeeddd93556dd96afb3a611f7f663";
			String deviceSerialNumber="C0180602211708090002";
			Integer surveyNo=4;
			String probeId="7";   //探测器ID
			String collectorName="杀虫";  // 采集器去名称
			Boolean controlCondition=true;  //控制条件 0为小于阈值时  1为大于阈值时
			int controlValue=20;  //控制阈值
		    Boolean controllerStatus=false;
				 
			String createTime=df.format(new Date());    //创建日期
			String updateTime=df.format(new Date());    //更新日期
			
			ParameterControllerServiceImpl parameterControllerService=(ParameterControllerServiceImpl) ctx.getBean("parameterControllerService");
			ParameterControllerInfoBean parameterControllerInfoBean=new ParameterControllerInfoBean();
			parameterControllerInfoBean.setId(id);
			parameterControllerInfoBean.setControllerId(controllerId);
			parameterControllerInfoBean.setDeviceSerialNumber(deviceSerialNumber);
			parameterControllerInfoBean.setSurveyNo(surveyNo);
			parameterControllerInfoBean.setProbeId(probeId);
			parameterControllerInfoBean.setCollectorName(collectorName);
			parameterControllerInfoBean.setControlCondition(controlCondition);
			parameterControllerInfoBean.setControlValue(controlValue);
			parameterControllerInfoBean.setControllerStatus(controllerStatus);
			parameterControllerInfoBean.setCreateTime(createTime);
			parameterControllerInfoBean.setUpdateTime(updateTime);
			
			parameterControllerService.addParameterControllerInfo(parameterControllerInfoBean);
			//parameterControllerService.updateParameterControllerInfo(parameterControllerInfoBean);
			//parameterControllerService.deleteParameterControllerInfoById(id);
		System.out.println("=================成功=======================");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("=================添加发生了错误=======================");
		}
		//
		// 初始化spring
		// try {
		// ApplicationContext ctx = new ClassPathXmlApplicationContext(
		// "applicationContext.xml");
		// List list =null;
		// List list1 = null;
		// 获取service的bean

		// CodeServiceImpl is = (CodeServiceImpl) ctx.getBean("codeService");
		// //获取码表数据
		// list=is.findAll();

		// System.out.println(list);
		// MassIfProductServiceImpl is = (MassIfProductServiceImpl)
		// ctx.getBean("massIfProductService");
		// list = is.getMassifAndDeviceList(null);
		// String[] aArray = new String[0];
		// System.out.println(BaseUtil.listToJsonStr(list, aArray,
		// MassifProductbean.class));
		// //下面这个可以不用看
		// ProbeSpecificDataServiceImpl isa = (ProbeSpecificDataServiceImpl)
		// ctx.getBean("probeSpecificDataService");
		// Map aaa= new HashMap<>();
		// aaa.put("dsn", "C0180602211708090002");
		// list = isa.findNewData(aaa);

		// ProbeServiceImpl isa = (ProbeServiceImpl)
		// ctx.getBean("probeServiceImpl");
		// Map aaa= new HashMap<String,String>();
		// aaa.put("deviceno", "C0180602211708090002");
		// list = isa.getProbeAndProbeData(aaa);

		// DeviceInfoServiceImpl isS = (DeviceInfoServiceImpl)
		// ctx.getBean("deviceInfoService");
		// Map aaa= new HashMap<>();
		// aaa.put("deviceno", "C0180602211708090002");
		// list = isS.getDeviceAndProbeList(aaa);
		// String[] aArray = new String[0];
		// System.out.println(BaseUtil.listToJsonStr(list, aArray,
		// DeviceInfoBean.class));

		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
