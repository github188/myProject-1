package com.ssm.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ProbeSpecificDataMonthBean implements Serializable {
	/**
	 * 数据ID
	 */
	private int id;
	private String pkProbeId;
	private String pkDevicesId;
	private Float probeData;
	private String probeUnit;
	private String createTime;
	private String updateTime;
	private int surveyNo;
	
	public ProbeSpecificDataMonthBean(int id, String pkProbeId,
			String pkDevicesId, Float probeData,String probeUnit, String createTime,String updateTime,int surveyNo) {
		super();
		this.id = id;
		this.pkProbeId = pkProbeId;
		this.pkDevicesId = pkDevicesId;
		this.probeData = probeData;
		this.probeUnit = probeUnit;
		this.createTime=createTime;
		this.updateTime=updateTime;
		this.surveyNo=surveyNo;
	}

	@Override
	public String toString() {
		return "ProbeSpecificDataBean [id=" + id + ", pkProbeId=" + pkProbeId
				+ ", pkDevicesId=" + pkDevicesId + ", probeData=" + probeData
				+ ", probeUnit=" + probeUnit + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

	public ProbeSpecificDataMonthBean() {
		super();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getSurveyNo() {
		return surveyNo;
	}

	public void setSurveyNo(int surveyNo) {
		this.surveyNo = surveyNo;
	}
	

}

