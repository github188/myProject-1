package com.ssm.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.annotation.Resource;


public class TimerControllerInfoBean implements Serializable {
//时间控制参数实体类
	private String id;
	private String controllerId;  //控制器ID
	private String deviceSerialNumber;   //探测设备ID
	private Integer surveyNo;   //探头编号
    private Integer intervalDays;   //间隔天数
	private String controlPeriodStart;  //  控制时段开始点 这个没有年月日无所谓
	private String controlPeriodEnd; //  控制时段结束点
	private Boolean isRegular;  //是否定期
	private String regularStartDate;  //定期开始时间
	private String regularEndDate;   //定期结束时间
	private Boolean controllerStatus;    //控制器状态
	private String  createTime;      //创建日期
	private String  updateTime; 	 //更新日期
	
	public TimerControllerInfoBean() {
		super();
	}



	@Override
	public String toString() {
		return "TimerControllerInfoBean [id=" + id + ", controllerId=" + controllerId + ", deviceSerialNumber="
				+ deviceSerialNumber + ", surveyNo=" + surveyNo + ", intervalDays=" + intervalDays
				+ ", controlPeriodStart=" + controlPeriodStart + ", controlPeriodEnd=" + controlPeriodEnd
				+ ", isRegular=" + isRegular + ", regularStartDate=" + regularStartDate + ", regularEndDate="
				+ regularEndDate + ", controllerStatus=" + controllerStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getControllerId() {
		return controllerId;
	}



	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}



	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}



	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}



	public Integer getSurveyNo() {
		return surveyNo;
	}



	public void setSurveyNo(Integer surveyNo) {
		this.surveyNo = surveyNo;
	}



	public Integer getIntervalDays() {
		return intervalDays;
	}



	public void setIntervalDays(Integer intervalDays) {
		this.intervalDays = intervalDays;
	}



	public String getControlPeriodStart() {
		return controlPeriodStart;
	}



	public void setControlPeriodStart(String controlPeriodStart) {
		this.controlPeriodStart = controlPeriodStart;
	}



	public String getControlPeriodEnd() {
		return controlPeriodEnd;
	}



	public void setControlPeriodEnd(String controlPeriodEnd) {
		this.controlPeriodEnd = controlPeriodEnd;
	}



	public Boolean getIsRegular() {
		return isRegular;
	}



	public void setIsRegular(Boolean isRegular) {
		this.isRegular = isRegular;
	}



	public String getRegularStartDate() {
		return regularStartDate;
	}



	public void setRegularStartDate(String regularStartDate) {
		this.regularStartDate = regularStartDate;
	}



	public String getRegularEndDate() {
		return regularEndDate;
	}



	public void setRegularEndDate(String regularEndDate) {
		this.regularEndDate = regularEndDate;
	}



	public Boolean getControllerStatus() {
		return controllerStatus;
	}



	public void setControllerStatus(Boolean controllerStatus) {
		this.controllerStatus = controllerStatus;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
