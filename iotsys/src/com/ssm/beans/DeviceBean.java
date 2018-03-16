package com.ssm.beans;

import java.io.Serializable;

public class DeviceBean implements Serializable {
	
	private String id;
	private String deviceNo;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", DeviceNo=" + deviceNo + "]";
	}
}

