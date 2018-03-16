package com.ssm.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ProbeSpecificDataBean implements Serializable {
	/**
	 * 数据ID
	 */
	private String id;
	private String pkProbeId;
	private String pkDevicesId;
	private Float probeData;
	private String deviceSerialNumber;
	private int surveyNo;
	private String probeUnit;
	private String surveyName;
	private String createTime;
	private String updateTime;
	
	
	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
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

	public ProbeSpecificDataBean(String id, String pkProbeId,
			String pkDevicesId, Float probeData, String probeType,
			String probeUnit, String surveyName) {
		super();
		this.id = id;
		this.pkProbeId = pkProbeId;
		this.pkDevicesId = pkDevicesId;
		this.probeData = probeData;
		this.probeUnit = probeUnit;
		this.surveyName = surveyName;
	}

	@Override
	public String toString() {
		return "ProbeSpecificDataBean [id=" + id + ", pkProbeId=" + pkProbeId
				+ ", pkDevicesId=" + pkDevicesId + ", probeData=" + probeData
				+ ", deviceSerialNumber=" + deviceSerialNumber + ", surveyNo="
				+ surveyNo + ", probeUnit=" + probeUnit + ", surveyName="
				+ surveyName + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]\r\n";
	}

	public ProbeSpecificDataBean() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPkProbeId() {
		return pkProbeId;
	}
	public void setPkProbeId(String pkProbeId) {
		this.pkProbeId = pkProbeId;
	}
	public String getPkDevicesId() {
		return pkDevicesId;
	}
	public void setPkDevicesId(String pkDevicesId) {
		this.pkDevicesId = pkDevicesId;
	}
	public Float getProbeData() {
		return probeData;
	}
	public void setProbeData(Float probeData) {
		this.probeData = probeData;
	}
	
	public String getProbeUnit() {
		return probeUnit;
	}
	public void setProbeUnit(String probeUnit) {
		this.probeUnit = probeUnit;
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

