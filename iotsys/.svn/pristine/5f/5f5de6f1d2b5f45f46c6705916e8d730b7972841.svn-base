package com.ssm.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.DeviceInfoBean;
import com.ssm.beans.DeviceModelBean;
import com.ssm.beans.PowerControllerBean;
import com.ssm.beans.ProbeBean;
import com.ssm.beans.ProbeDefaultBean;
import com.ssm.service.IDeviceInfoService;
import com.ssm.service.IDeviceModelService;
import com.ssm.service.IPowerControllerService;
import com.ssm.service.IProbeDefaultService;
import com.ssm.service.IProbeService;
import com.ssm.util.BaseUtil;
import com.ssm.util.HttpClientUtil;
import com.ssm.util.PropertiesUtil;

@Controller("apiDeviceInfoAction")
public class DeviceInfoAction  extends BaseAction{
		
	@Resource
	private IDeviceInfoService deviceInfoService;
	@Resource
	private IProbeService probeService;
	@Resource
	private IDeviceModelService deviceModelService;
	@Resource
	private IPowerControllerService powerControllerService;
	@Resource
	private IProbeDefaultService probeDefaultService;
	
	private DeviceInfoBean deviceInfo;//
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	/**
	 * 添加采集器
	 * @throws Exception 
	 */
	public void addDeviceInfo() throws Exception {
		log.info("-----添加采集器---【apiDeviceInfoAction_addDeviceInfo.do】");
		ApiDomain domain = new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("aORb", deviceInfo.getSerialNumber());//模糊查询做精确用
			params.put("enterpriseId", deviceInfo.getEnterpriseId().split("ˇ")[0]);
			List<DeviceInfoBean> device = this.deviceInfoService.findByCondition(params);
			if (device!=null && device.size()>0) {
				domain.setCode("002");
				domain.setMsg("采集器设备序列号重复");
				return;
			}
			HttpServletRequest req = getRequest();
			//探测器
			String[] surveyName = req.getParameterValues("surveyName");
			String[] unit = req.getParameterValues("unit");
			String[] surveyNo = req.getParameterValues("surveyNo");
			String[] surveyMin = req.getParameterValues("surveyMin");
			String[] surveyMax = req.getParameterValues("surveyMax");
			String[] probeDefaultIds = req.getParameterValues("probeDefaultIds");
			if (surveyName!=null && surveyName.length>0) {
				List<ProbeBean> probeBeans = new ArrayList<>();
				int i = 0;
				for (i = 0; i < surveyName.length; i++) {
					ProbeBean probe = new ProbeBean();
					probe.setId(BaseUtil.getUUID());
					probe.setSurveyName(surveyName[i]);
					probe.setUnit(unit[i]);
					probe.setSurveyNo(Integer.parseInt(surveyNo[i]));
					probe.setSurveyMin(surveyMin[i]);
					probe.setSurveyMax(surveyMax[i]);
					probe.setProbeDefaultId(probeDefaultIds[i]);
					probe.setSortNo(i);
					probe.setDeviceSerialNumber(deviceInfo.getSerialNumber());
					
					probeBeans.add(probe);
				}
				deviceInfo.setProbeNumber(i);
				probeService.insertMany(probeBeans);
			}else {
				deviceInfo.setProbeNumber(0);
			}
			//控制器
			String[] controllerNames = req.getParameterValues("controllerNames");
			String[] controllerNos = req.getParameterValues("controllerNos");
			String[] controllerPics = req.getParameterValues("controllerPics");
			if (controllerNames!=null && controllerNames.length>0) {
				List<PowerControllerBean> list = new ArrayList<PowerControllerBean>();
				int j = 0;
				for (j = 0; j < controllerNames.length; j++) {
					PowerControllerBean bean = new PowerControllerBean();
					bean.setId(BaseUtil.getUUID());
					bean.setControllerName(controllerNames[j]);
					bean.setSurveyNo(Integer.parseInt(controllerNos[j]));
					bean.setSortNo(j);
					bean.setStatus("0");
					bean.setPowerSwitch("0");
					bean.setDeviceSerialNumber(deviceInfo.getSerialNumber());
					bean.setIcoName(controllerPics[j]);
					list.add(bean);
				}
				this.powerControllerService.BatchInsert(list);
				
				deviceInfo.setControllerNumber(j);
			}else {
				deviceInfo.setControllerNumber(0);
			}
			
			deviceInfo.setId(BaseUtil.getUUID());
			String[] en =deviceInfo.getEnterpriseId().split("ˇ");
			deviceInfo.setEnterpriseId(en[0]);
			deviceInfo.setEnterpriseName(en[1]);
			deviceInfo.setUpdateTime(BaseUtil.getCurrentTimestr());
			this.deviceInfoService.insertDevice(deviceInfo);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("增加摄像头  修改摄像头发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("-----根据主键查询摄像头结束----");
	}
	/**
	 * 修改采集器
	 * @throws Exception 
	 */
	public void editDeviceInfo() throws Exception {
		log.info("-----修改采集器---【apiDeviceInfoAction_addDeviceInfo.do】");
		ApiDomain domain = new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("aORb", deviceInfo.getSerialNumber());//模糊查询做精确用
			params.put("enterpriseId", deviceInfo.getEnterpriseId().split("ˇ")[0]);
			List<DeviceInfoBean> device = this.deviceInfoService.findByCondition(params);
			if (device!=null && device.size()>0 && !device.get(0).getId().equals(deviceInfo.getId())) {
				domain.setCode("002");
				domain.setMsg("采集器设备序列号重复");
				return;
			}
			HttpServletRequest req = getRequest();
			String[] id = req.getParameterValues("id");
			String deleteIds = req.getParameter("deleteIds");
			String[] surveyName = req.getParameterValues("surveyName");
			String[] unit = req.getParameterValues("unit");
			String[] surveyNo = req.getParameterValues("surveyNo");
			String[] surveyMin = req.getParameterValues("surveyMin");
			String[] surveyMax = req.getParameterValues("surveyMax");
			String[] probeDefaultIds = req.getParameterValues("probeDefaultIds");
			List<String> ids = Arrays.asList(deleteIds.split(","));
			//删除操作
			if (deleteIds!=null && deleteIds.length()>0) {
				probeService.deleteByIds(ids);
				powerControllerService.deleteByIds(ids);
			}
			
			if (surveyName!=null && surveyName.length>0) {
				List<ProbeBean> probeBeans = new ArrayList<>();
				int i = 0;
				for (i = 0; i < surveyName.length; i++) {
					ProbeBean probe = new ProbeBean();
					if ("a".equals(id[i])) {
						probe.setId(BaseUtil.getUUID());
					}else {
						probe.setId(id[i]);
					}
					probe.setSurveyName(surveyName[i]);
					probe.setUnit(unit[i]);
					probe.setSurveyNo(Integer.parseInt(surveyNo[i]));
					probe.setSurveyMin(surveyMin[i]);
					probe.setSurveyMax(surveyMax[i]);
					probe.setProbeDefaultId(probeDefaultIds[i]);
					probe.setSortNo(i);
					probe.setDeviceSerialNumber(deviceInfo.getSerialNumber());
					if ("a".equals(id[i])) {
						probeBeans.add(probe);
					}else {
						probe.setUpdateTime(BaseUtil.getCurrentTimestr());
						probeService.updateProbe(probe);
					}
				}
				if (probeBeans.size()>0) {
					probeService.insertMany(probeBeans);
				}
				
				deviceInfo.setProbeNumber(i);
			}else {
				deviceInfo.setProbeNumber(0);
			}
			
			//控制器
			String[] controllerNames = req.getParameterValues("controllerNames");
			String[] controllerNos = req.getParameterValues("controllerNos");
			String[] controllerPics = req.getParameterValues("controllerPics");
			String[] controllerIds = req.getParameterValues("controllerIds");
			if (controllerNames!=null && controllerNames.length>0) {
				List<PowerControllerBean> list = new ArrayList<PowerControllerBean>();
				List<PowerControllerBean> list1 = new ArrayList<PowerControllerBean>();
				int j = 0;
				for (j = 0; j < controllerNames.length; j++) {
					PowerControllerBean bean = new PowerControllerBean();
					if ("a".equals(controllerIds[j])) {
						bean.setId(BaseUtil.getUUID());
					}else {
						bean.setId(controllerIds[j]);
					}
					bean.setControllerName(controllerNames[j]);
					bean.setSurveyNo(Integer.parseInt(controllerNos[j]));
					bean.setSortNo(j);
					bean.setStatus("0");
					bean.setPowerSwitch("0");
					bean.setDeviceSerialNumber(deviceInfo.getSerialNumber());
					bean.setIcoName(controllerPics[j]);
					if ("a".equals(controllerIds[j])) {
						list.add(bean);
					}else {
						bean.setUpdateTime(BaseUtil.getCurrentTimestr());
						list1.add(bean);
					}
					
				}
				if (list1.size()>0) {
					this.powerControllerService.UpdateBatchControllers(list1);
				}
				if (list.size()>0) {
					this.powerControllerService.BatchInsert(list);
				}
				
				deviceInfo.setControllerNumber(j);
			}else {
				deviceInfo.setControllerNumber(0);
			}
			
			String[] en =deviceInfo.getEnterpriseId().split("ˇ");
			deviceInfo.setEnterpriseId(en[0]);
			deviceInfo.setEnterpriseName(en[1]);
			deviceInfo.setUpdateTime(BaseUtil.getCurrentTimestr());
			this.deviceInfoService.updateDevice(deviceInfo);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("修改采集器发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("-----修改采集器结束----");
	}
	/**
	 * 查询全部采集器
	 */
	public void findAll() {
		log.info("-----查询全部采集器---【apiDeviceInfoAction_findAll.do】");
		try {
			List<DeviceInfoBean> list = deviceInfoService.findAll();
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					if(getRedisTemplate().hasKey(list.get(i).getSerialNumber())){
						list.get(i).setEnterpriseId("正常");
					}else{
						list.get(i).setEnterpriseId("不在线");
					}
				}
			}
			this.printList(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询全部采集器发生异常："+e.getMessage(),e);
		}
		log.info("-----修改采集器结束----");
	}
	/**
	 * 查询全部采集器
	 */
	public void findAllByCondition() {
		log.info("-----查询全部采集器---【apiDeviceInfoAction_findAll.do】");
		try {
			HttpServletRequest req = getRequest();
			String enterpriseId = req.getParameter("enterpriseId");
			String aORb = req.getParameter("aORb");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("enterpriseId", enterpriseId);
			params.put("aORb", aORb);
			List<DeviceInfoBean> list = deviceInfoService.findByCondition(params);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					if(getRedisTemplate().hasKey(list.get(i).getSerialNumber())){
						list.get(i).setEnterpriseId("正常");
					}else{
						list.get(i).setEnterpriseId("不在线");
					}
				}
			}
			this.printList(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询全部采集器发生异常："+e.getMessage(),e);
		}
		log.info("-----查询全部采集器结束----");
	}
	/**
	 * 查询一个采集器以及探测器
	 * @throws Exception 
	 */
	public void findByIdAllInfo() throws Exception {
		log.info("-----查询全部采集器---【apiDeviceInfoAction_findByIdAllInfo.do】");
		ApiDomain domain = new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			HttpServletRequest req = getRequest();
			String deviceId = req.getParameter("deviceId");
			DeviceInfoBean deviceInfoBean = this.deviceInfoService.findById(deviceId);
			List<ProbeBean> probeBeans = this.probeService.findByDeviceSerialNumber(deviceInfoBean.getSerialNumber());
			List<PowerControllerBean> PowerControllerBean = this.powerControllerService.findPowerByDeviceSerialNumber(deviceInfoBean.getSerialNumber());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("probeBeans", probeBeans);
			map.put("deviceInfoBean", deviceInfoBean);
			map.put("PowerControllerBean", PowerControllerBean);
			domain.setData(map);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("查询一个采集器以及探测器发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("-----查询一个采集器以及探测器结束----");
	}
	/**
	 * 访问mogo项目 查询全部企业
	 */
	public void getAllEnterprise() {
		log.info("-----访问mogo项目 查询全部企业---【apiDeviceInfoAction_getAllEnterprise.do】");
		try {
			JSONArray json = HttpClientUtil.httpPostArr(PropertiesUtil.getAppConfig("myMongo3")+"enterprise/enterpriseAction_apiFindAll2.do", null, false);
			this.printStr(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("访问mogo项目 查询全部企业发生异常："+e.getMessage(),e);
		}
		log.info("-----访问mogo项目 查询全部企业结束----");
	}
	/**
	 * 查询设备类型
	 * @throws Exception 
	 */
	public void findAllDeviceModel() throws Exception {
		log.info("-----查询设备类型---【apiDeviceInfoAction_findAllDeviceModel.do】");
		ApiDomain domain = new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			List<DeviceModelBean> deviceModels = this.deviceModelService.findAll();
			List<ProbeDefaultBean> probeDefaults=probeDefaultService.findAll();
			map.put("deviceModels", deviceModels);
			map.put("probeDefaults", probeDefaults);
			domain.setData(map);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("查询设备类型发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("-----查询设备类型结束----");
	}
	
	/**
	 * 根据ID批量更新探测器数据以及控制器数据
	 * @throws Exception
	 */
	public  void BatchUpdateDevice() throws Exception{
		log.info("-----------根据ID批量更新探测器及控制器接口开始---------【apiDeviceInfoAction_BatchUpdateDevice.do】");
		ApiDomain domain = new ApiDomain();
		String[] ids = getRequest().getParameterValues("ids");//探测器id
		String[] surveyNos = getRequest().getParameterValues("surveyNos");//探测器通道号
		String[] surveyNames = getRequest().getParameterValues("surveyNames");//探测器名称
		String[] units = getRequest().getParameterValues("units");//探测器单位
		String[] surveyMins = getRequest().getParameterValues("surveyMins");//探测器最小范围
		String[] surveyMaxs = getRequest().getParameterValues("surveyMaxs");//探测器最大范围
		String[] probeDefaultIds=getRequest().getParameterValues("probeDefaultIds");
		String[] controllerIds = getRequest().getParameterValues("controllerIds");//控制器ID
		String[] controllerPics = getRequest().getParameterValues("controllerPics");//控制器图片名称
		String[] controllerNames = getRequest().getParameterValues("controllerNames");//控制器名称
		String[] controllerNos = getRequest().getParameterValues("controllerNos");//控制器通道号
		String deviceNames = getRequest().getParameter("deviceNames");//探测器id
		String deviceId = getRequest().getParameter("deviceId");//探测器id
		List<ProbeBean> probes=new ArrayList<ProbeBean>();
		List<PowerControllerBean> powers=new ArrayList<PowerControllerBean>();
		try{
			//根据采集器名称是否变更进行更新

			DeviceInfoBean device = deviceInfoService.findById(deviceId);
			if(!device.getDeviceName().equals(deviceNames)){
				device.setDeviceName(deviceNames);
				device.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				deviceInfoService.updateDevice(device);
			}
			if(ids!=null&&ids.length>0){
				for(int i=0;i<ids.length;i++){
				    ProbeBean probe=new ProbeBean();
				    probe.setId(ids[i]);
				    probe.setSurveyName(surveyNames[i]);
				    probe.setSurveyNo(Integer.valueOf(surveyNos[i]));
				    probe.setUnit(units[i]);
				    probe.setSortNo(i+1);
			    	probe.setSurveyMin(surveyMins[i]);
		 		    probe.setSurveyMax(surveyMaxs[i]);
		 		    probe.setProbeDefaultId(probeDefaultIds[i]);
				    probe.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				    probes.add(probe);
			     }
				this.probeService.updateBatch(probes);
			}
			if(controllerIds!=null&&controllerIds.length>0){
				for(int j=0;j<controllerIds.length;j++){
				    PowerControllerBean po=new PowerControllerBean();
				    po.setId(controllerIds[j]);
				    po.setSortNo(j+1);
				    po.setControllerName(controllerNames[j]);
			    	po.setIcoName(controllerPics[j]);
				    po.setSurveyNo(Integer.valueOf(controllerNos[j]));
			     	po.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			     	powers.add(po);
			     }
				this.powerControllerService.UpdateBatchControllers(powers);
			}
			domain.setCode("000");
			domain.setMsg("操作成功");
		}catch(Exception e){
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("根据ID批量更新探测器及控制器发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}
		log.info("-----------根据ID批量更新探测器及控制器接口结束---------");
	}
	
	/**
	 * 根据ID删除设备
	 * @throws Exception
	 */
	public  void deleteDevice() throws Exception{
		log.info("-----------根据ID删除设备接口开始---------【apiDeviceInfoAction_deleteDevice.do】");
		ApiDomain domain = new ApiDomain();
		String deviceId = getRequest().getParameter("id");//设备序列号
		try{
			deviceInfoService.deleteById(deviceId);
			 probeService.deleteByDeviceSer(deviceId);
			 powerControllerService.deleteByDeviceSer(deviceId);
			domain.setCode("000");
			domain.setMsg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("根据ID删除设备发生异常："+e.getMessage(),e);
		}finally{
				this.printObjectNoJsonp(domain);
		}
			log.info("-----------根据ID删除设备接口结束---------");
	}
	
	public void findBySerialNumber() throws Exception{
		log.info("-----------根据设备唯一序列号查找设备接口开始---------【apiDeviceInfoAction_findBySerialNumber.do】");
		ApiDomain domain = new ApiDomain();
		String serialNumber = getRequest().getParameter("deviceId");//设备序列号
		try{
			DeviceInfoBean bean=deviceInfoService.findBySerialNumber(serialNumber);
			domain.setCode("000");
			domain.setMsg("查询成功");
			domain.setData(bean);
		}catch(Exception e){
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("根据设备唯一序列号查找设备发生异常："+e.getMessage(),e);
		}finally{
				this.printObjectNoJsonp(domain);
		}
			log.info("-----------根据设备唯一序列号查找设备接口结束---------");
	}
	
	
	public DeviceInfoBean getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(DeviceInfoBean deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
}
