package com.ssm.beans;

import java.util.List;

import com.rabbitmq.client.AMQP.Basic.Get;

/**
 * 区块和采集器关联表
 * 
 * @author Smile
 * 
 * @date
 */
public class MassifProductbean {
	private String Id;// 数据主键
	private String massifId;// trace(mongodb)项目那边地块的ID
	private String deviceSerialNumber;// 设备的序号
	private Integer deviceType;// 地块添加的设备类型（视频/探头）1：物联网设备；2：摄像头
	private String enterpriseId;// 企业id
	private String enterpriseName;// 企业名称
	private String baseId;// 基地Id
	private String baseName;// 基地名称
	private String massIfName;// 地块名称
	private String createTime;// 创建时间
	private String uploadTime;// 更新时间
	private Integer count;//设备统计数量专用字段
	public List<DeviceInfoBean> deviceInfoList; //地块下面的设备集合
	private List<CameraInfoBean> cameraInfoList;//地块下面的摄像头集合
	
	
	public List<CameraInfoBean> getCameraInfoList() {
		return cameraInfoList;
	}

	public void setCameraInfoList(List<CameraInfoBean> cameraInfoList) {
		this.cameraInfoList = cameraInfoList;
	}

	public List<DeviceInfoBean> getDeviceInfoList() {
		return deviceInfoList;
	}

	public void setDeviceInfoList(List<DeviceInfoBean> deviceInfoList) {
		this.deviceInfoList = deviceInfoList;
	}

	public List<ProbeSpecificDataBean> probeSpecificDataBean;

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public List<ProbeSpecificDataBean> getProbeSpecificDataBean() {
		return probeSpecificDataBean;
	}

	public void setProbeSpecificDataBean(
			List<ProbeSpecificDataBean> probeSpecificDataBean) {
		this.probeSpecificDataBean = probeSpecificDataBean;
	}

	public MassifProductbean() {
		super();
	}

	public MassifProductbean(String id, String massifId, Integer deviceType,
			String createTime, String uploadTime, String enterpriseId,
			String enterpriseName, String baseId, String baseName,
			String massIfName) {
		super();
		Id = id;
		this.massifId = massifId;
		this.deviceType = deviceType;
		this.createTime = createTime;
		this.uploadTime = uploadTime;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.baseId = baseId;
		this.baseName = baseName;
		this.massIfName = massIfName;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getMassifId() {
		return massifId;
	}

	public void setMassifId(String massifId) {
		this.massifId = massifId;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getMassIfName() {
		return massIfName;
	}

	public void setMassIfName(String massIfName) {
		this.massIfName = massIfName;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "MassifProductbean [Id=" + Id + ", massifId=" + massifId
				+ ", deviceSerialNumber=" + deviceSerialNumber
				+ ", deviceType=" + deviceType + ", enterpriseId="
				+ enterpriseId + ", enterpriseName=" + enterpriseName
				+ ", baseId=" + baseId + ", baseName=" + baseName
				+ ", massIfName=" + massIfName + ", createTime=" + createTime
				+ ", uploadTime=" + uploadTime + ", \n\r deviceInfoList="
				+ deviceInfoList + ",\n\r probeSpecificDataBean="
				+ probeSpecificDataBean + "]\n\r ";
	}

	

}
