package com.ssm.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ParameterControllerInfoBean;
import com.ssm.beans.SmartControllerBean;
import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.service.IParameterControllerService;
import com.ssm.service.ISmartControllerService;
import com.ssm.service.ITimerControllerService;
import com.ssm.service.impl.ParameterControllerServiceImpl;
import com.ssm.util.BaseUtil;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.objectweb.asm.commons.TryCatchBlockSorter;

@Controller("SmartControllerAction")
@Scope("prototype")
public class SmartControllerAction extends BaseAction {

	@Resource
	private ISmartControllerService smartControllerService;
	
	@Resource
	private ITimerControllerService timerControllerService;
	
	@Resource
	private IParameterControllerService parameterControllerService;
	
	private Logger log=Logger.getLogger(this.getClass());
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	//选择所有数据
	public void selectAll() throws Exception
	{
		log.info("-----选择所有信息---【SmartControllerAction_selectAll.do】");
		List<SmartControllerBean> smartControllerBeansList=smartControllerService.selectAll();
		
	}
	
	/*
	---------------时间控制--------------------------
	
	*/
	//添加时间控制数据
	public void addTimerControllerInfo()
	{
		log.info("-----添加时间控制数据---【SmartControllerAction_addTimerControllerInfo.do】");
		try {

			String id=BaseUtil.getUUID(); //获取ID
			
			//所属控制器ID
			String controllerId=getRequest().getParameter("timerControllerId");
			
			//设备序列号
			String deviceSerialNumber=getRequest().getParameter("timerDeviceSerialNumber");
			Integer surveyNo=Integer.parseInt(getRequest().getParameter("timerSurveyNo"));
			Integer intervalDays=Integer.parseInt(getRequest().getParameter("timerIntervalDays"));;
			String controlPeriodStart=getRequest().getParameter("timerControlPeriodStart");
			String controlPeriodEnd=getRequest().getParameter("timerControlPeriodEnd");
			Boolean isRegular=Boolean.parseBoolean(getRequest().getParameter("timerIsRegular"));
			String regularStartDate=getRequest().getParameter("timerRegularStartDate");
			String regularEndDate=getRequest().getParameter("timerRegularEndDate");
			
			//时间控制服务状态,默认为关
			Boolean controllerStatus=false;
			
			String createTime=df.format(new Date());
			String updateTime=df.format(new Date());
			
			//添加操作
			TimerControllerInfoBean timerControllerInfoBean=new TimerControllerInfoBean();
			timerControllerInfoBean.setId(id);
			timerControllerInfoBean.setControllerId(controllerId);		
			timerControllerInfoBean.setDeviceSerialNumber(deviceSerialNumber);
			timerControllerInfoBean.setSurveyNo(surveyNo);
			timerControllerInfoBean.setIntervalDays(intervalDays);
			
			timerControllerInfoBean.setControlPeriodStart(controlPeriodStart);
			timerControllerInfoBean.setControlPeriodEnd(controlPeriodEnd);
			timerControllerInfoBean.setIsRegular(isRegular);
			timerControllerInfoBean.setRegularStartDate(regularStartDate);
			timerControllerInfoBean.setRegularEndDate(regularEndDate);
			timerControllerInfoBean.setControllerStatus(controllerStatus);
			
			timerControllerInfoBean.setCreateTime(createTime);
			timerControllerInfoBean.setUpdateTime(updateTime);
			
			timerControllerService.addTimerControllerInfo(timerControllerInfoBean);
			
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================添加时间控制数据成功======================="));
			
		} catch (Exception e) {
			// TODO: handle exception
		
			boolean returnMark=false;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，添加失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
		}
	
	}
	
	//修改时间控制数据
	public void updateTimerControllerInfo() 
	{
		
		log.info("-----修改时间控制数据---【SmartControllerAction_updateTimerControllerInfo.do】");
		try {
			String id=getRequest().getParameter("timerId"); //获取ID

			//所属控制器ID
			String controllerId=getRequest().getParameter("timerControllerId");
			//设备序列号
			String deviceSerialNumber=getRequest().getParameter("timerDeviceSerialNumber");
			Integer surveyNo=Integer.parseInt(getRequest().getParameter("timerSurveyNo"));
			Integer intervalDays=Integer.parseInt(getRequest().getParameter("timerIntervalDays"));;
			String controlPeriodStart=getRequest().getParameter("timerControlPeriodStart");
			String controlPeriodEnd=getRequest().getParameter("timerControlPeriodEnd");
			Boolean isRegular=Boolean.parseBoolean(getRequest().getParameter("timerIsRegular"));
			String regularStartDate=getRequest().getParameter("timerRegularStartDate");
			String regularEndDate=getRequest().getParameter("timerRegularEndDate");
			
			//控制器状态
	
			String updateTime=df.format(new Date());
			
			//添加操作
			TimerControllerInfoBean timerControllerInfoBean=new TimerControllerInfoBean();
			timerControllerInfoBean.setId(id);
			timerControllerInfoBean.setControllerId(controllerId);		
			timerControllerInfoBean.setDeviceSerialNumber(deviceSerialNumber);
			timerControllerInfoBean.setSurveyNo(surveyNo);
			timerControllerInfoBean.setIntervalDays(intervalDays);
			
			timerControllerInfoBean.setControlPeriodStart(controlPeriodStart);
			timerControllerInfoBean.setControlPeriodEnd(controlPeriodEnd);
			timerControllerInfoBean.setIsRegular(isRegular);
			timerControllerInfoBean.setRegularStartDate(regularStartDate);
			timerControllerInfoBean.setRegularEndDate(regularEndDate);
			
			timerControllerInfoBean.setUpdateTime(updateTime);
			
			timerControllerService.updateTimerControllerInfo(timerControllerInfoBean);
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================修改时间控制数据成功======================="));
		} catch (Exception e) {
			// TODO: handle exception
			boolean returnMark=false;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，修改失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
	}
	
	//删除时间控制数据
	public void deleteTimerControllerInfo() 
	{
		log.info("-----修改时间控制数据---【SmartControllerAction_deleteTimerControllerInfo.do】");
		
		try {
			
			String id=getRequest().getParameter("id");
			timerControllerService.deleteTimerControllerInfoById(id);
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================删除时间控制数据成功======================="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			boolean returnMark=false;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，删除失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
			
		}
	}
	/*
	---------------参数控制--------------------------
	*/
	//添加参数控制数据
	public void addParameterContrololerInfo() 
	{
		log.info("-----修改时间控制数据---【SmartControllerAction_addParameterContrololerInfo.do】");
		try {
			String id=BaseUtil.getUUID();
			String controllerId=getRequest().getParameter("parameterContrllerId");
			String deviceSerialNumber=getRequest().getParameter("parameterDeviceSerialNumber");
			Integer surveyNo=Integer.parseInt(getRequest().getParameter("parameterSurveyNo"));
			String probeId=getRequest().getParameter("parameterProbeId");   //探测器ID
			String collectorName=getRequest().getParameter("parameterCollectorName");  // 采集器去名称
			Boolean controlCondition=Boolean.parseBoolean(getRequest().getParameter("parameterControlCondition"));  //控制条件 0为小于阈值时  1为大于阈值时
			int controlValue=Integer.parseInt(getRequest().getParameter("parameterControlValue"));;  //控制阈值
		    
			//参数控制服务状态，默认为关
			Boolean controllerStatus=false;
			String createTime=df.format(new Date());    //创建日期
			String updateTime=df.format(new Date());    //更新日期
			
			//添加操作
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
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================添加参数控制数据成功======================="));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			boolean returnMark=false;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，添加失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
		}
	
	}

	//修改时间控制数据
	public void updateParameterControllerInfo()
	{
		log.info("-----修改时间控制数据---【SmartControllerAction_updateParameterControllerInfo.do】");
		try {
			String id=getRequest().getParameter("parameterId");
			String controllerId=getRequest().getParameter("parameterContrllerId");
			String deviceSerialNumber=getRequest().getParameter("parameterDeviceSerialNumber");
			Integer surveyNo=Integer.parseInt(getRequest().getParameter("parameterSurveyNo"));
			String probeId=getRequest().getParameter("parameterProbeId");   //探测器ID
			String collectorName=getRequest().getParameter("parameterCollectorName");  // 采集器去名称
			Boolean controlCondition=Boolean.parseBoolean(getRequest().getParameter("parameterControlCondition"));  //控制条件 0为小于阈值时  1为大于阈值时
			int controlValue=Integer.parseInt(getRequest().getParameter("parameterControlValue"));;  //控制阈值
		    
			String updateTime=df.format(new Date());    //更新日期
			
			//添加操作
			ParameterControllerInfoBean parameterControllerInfoBean=new ParameterControllerInfoBean();
			parameterControllerInfoBean.setId(id);
			parameterControllerInfoBean.setControllerId(controllerId);
			parameterControllerInfoBean.setDeviceSerialNumber(deviceSerialNumber);
			parameterControllerInfoBean.setSurveyNo(surveyNo);
			parameterControllerInfoBean.setProbeId(probeId);
			parameterControllerInfoBean.setCollectorName(collectorName);
			parameterControllerInfoBean.setControlCondition(controlCondition);
			parameterControllerInfoBean.setControlValue(controlValue);
			parameterControllerInfoBean.setUpdateTime(updateTime);
			
			parameterControllerService.updateParameterControllerInfo(parameterControllerInfoBean);
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================修改参数控制数据成功======================="));
		} catch (Exception e) {
			// TODO: handle exception
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，修改失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	//删除时间控制数据
	public void deleteParameterControllerInfo() 
	{
		log.info("-----修改时间控制数据---【SmartControllerAction_deleteParameterControllerInfo.do】");
		try {
			String id=getRequest().getParameter("id");
			parameterControllerService.deleteParameterControllerInfoById(id);
			//成功则向前台返回成功标记
			boolean returnMark=true;
			getRequest().setAttribute("returnMark", returnMark);
			log.info(("=================删除参数控制数据成功======================="));
		} catch (Exception e) {
			// TODO: handle exception
			boolean returnMark=false;
			getRequest().setAttribute("returnMark", returnMark);
			log.error(("=================发生错误，删除失败======================="));
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
	}
	
}
