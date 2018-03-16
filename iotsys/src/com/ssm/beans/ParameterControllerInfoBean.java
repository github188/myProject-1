package com.ssm.beans;

import java.io.Serializable;


public class ParameterControllerInfoBean implements Serializable {
	private String id;
	private String controllerId;  //控制器ID
	private String deviceSerialNumber;   //探测设备ID
	private int surveyNo;   //探头编号
	private String probeId;   //探测器ID
	private String collectorName;  // 采集器去名称
	private Boolean controlCondition;  //控制条件 0为小于阈值时  1为大于阈值时
	private int controlValue;  //控制阈值
	private Boolean controllerStatus;    //控制器状态
	private String  createTime;      //创建日期
	private String  updateTime; 	 //更新日期
	
	@Override
	public String toString() {
		return "ParameterControllerInfoBean [id=" + id + ", controllerIdString=" + controllerId
				+ ", deviceSerialNumber=" + deviceSerialNumber + ", surveyNo=" + surveyNo + ", probeId=" + probeId
				+ ", collectorName=" + collectorName + ", controlCondition=" + controlCondition + ", controlValue="
				+ controlValue + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
	public ParameterControllerInfoBean() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getControllerIdString() {
		return controllerId;
	}
	public void setControllerIdString(String controllerId) {
		this.controllerId = controllerId;
	}
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
	public int getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	public String getProbeId() {
		return probeId;
	}
	public void setProbeId(String probeId) {
		this.probeId = probeId;
	}
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public Boolean getControlCondition() {
		return controlCondition;
	}

	public void setControlCondition(Boolean controlCondition) {
		this.controlCondition = controlCondition;
	}

	public int getControlValue() {
		return controlValue;
	}
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
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
