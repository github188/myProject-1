package com.ssm.beans;

/**
 * 控制器表
 * 
 * @author Smile
 * 
 * @date
 */
public class PowerControllerBean {
	
	private String id;//ID
	private String controllerName;//设备名称
	private String deviceSerialNumber;//设备序号
	private Integer surveyNo;//通道号
	private String powerSwitch;//开关 开是3  关是0 默认关  只能是0 3
	private String status;//状态  默认0
	private String icoName;//控制器图标
	private Integer sortNo;//排序
	private String createTime;//创建时间
	private String updateTime;//更新时间
	private String comments;//描述
	
	

	public PowerControllerBean(){
		super();
	}
	


	public PowerControllerBean(String id, String controllerName,
			String deviceSerialNumber, Integer surveyNo, String powerSwitch,
			String status, String icoName, Integer sortNo, String createTime,
			String updateTime, String comments) {
		super();
		this.id = id;
		this.controllerName = controllerName;
		this.deviceSerialNumber = deviceSerialNumber;
		this.surveyNo = surveyNo;
		this.powerSwitch = powerSwitch;
		this.status = status;
		this.icoName = icoName;
		this.sortNo = sortNo;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.comments = comments;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
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

	public String getPowerSwitch() {
		return powerSwitch;
	}

	public void setPowerSwitch(String powerSwitch) {
		this.powerSwitch = powerSwitch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}
	
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



	@Override
	public String toString() {
		return "PowerControllerBean [id=" + id + ", controllerName="
				+ controllerName + ", deviceSerialNumber=" + deviceSerialNumber
				+ ", surveyNo=" + surveyNo + ", powerSwitch=" + powerSwitch
				+ ", status=" + status + ", icoName=" + icoName + ", sortNo="
				+ sortNo + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", comments=" + comments + "]";
	}

	
	
	
	

}
