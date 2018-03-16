package com.ssm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.CommonBean;
import com.ssm.beans.DeviceInfoBean;
import com.ssm.beans.MassifProductbean;
import com.ssm.beans.PowerControllerBean;
import com.ssm.beans.ProbeBean;
import com.ssm.beans.ProbeDefaultBean;
import com.ssm.service.impl.DeviceInfoServiceImpl;
import com.ssm.service.impl.MassIfProductServiceImpl;
import com.ssm.service.impl.PowerControllerServiceImpl;
import com.ssm.service.impl.ProbeDefaultServiceImpl;
import com.ssm.util.BaseUtil;
import com.ssm.util.HttpClientUtil;
import com.ssm.util.PropertiesUtil;
import com.ssm.util.StringUtils;

@Controller("apiMassifProductAction")
public class MassifProductAction extends BaseAction{
	
	
	@Resource
	private MassIfProductServiceImpl massIfProductService;
	@Resource
	private DeviceInfoServiceImpl deviceInfoService;
	@Resource
	private  PowerControllerServiceImpl powerControllerService;
	@Resource
	private ProbeDefaultServiceImpl probeDefaultService;
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	private MassifProductbean mass; //地块关联表对象
	
	private DeviceInfoBean deviceInfo;//设备对象
	
	
	
	/**
	 * 通过企业ID查找基地地块表
	 * @throws Exception
	 */
	public void findBaseByEnterprise() throws Exception{
		log.info("-----------根据企业ID获取企业基地信息接口开始---------【apiMassifProductAction_findBaseByEnterprise.do】");
		ApiDomain domain=new ApiDomain();
		try {
			List<MassifProductbean> list=massIfProductService.selectMassifProductByEnterpriseId(mass.getEnterpriseId());
			domain.setCode("000");
			domain.setMsg("查询成功");
			domain.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("根据企业ID获取企业基地信息发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}	
		log.info("-----------根据企业ID获取企业基地信息接口结束---------");
	}
	
	/**
	 * 通过企业ID和基地Id查找基地地块表
	 * @throws Exception
	 */
	public void findMassifByEnterpriseBase() throws Exception{
		log.info("-----------根据企业ID和基地ID获取企业基地信息接口开始---------【apiMassifProductAction_findMassifByEnterpriseBase.do】");
		ApiDomain domain=new ApiDomain();
		try {
			List<MassifProductbean> list=massIfProductService.selectMassifProductByEnterpriseIdAndBaseId(mass.getEnterpriseId(), mass.getBaseId());
			domain.setCode("000");
			domain.setMsg("查询成功");
			domain.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("根据企业ID和基地ID获取企业基地信息发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}	
		log.info("-----------根据企业ID和基地ID获取企业基地信息接口结束---------");
	}
	
	/**
	 * 根据条件不同动态查找基地地块表
	 * @throws Exception
	 */
	public void findMassifProductByColumns() throws Exception{
		log.info("-----------动态获取企业基地信息接口开始---------【apiMassifProductAction_findMassifProductByColumns.do】");
		ApiDomain domain=new ApiDomain();
		List<CommonBean> commons=new ArrayList<CommonBean>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		try {
			//企业ID不为空则放入动态参数中
			if(!StringUtils.isEmpty(mass.getEnterpriseId())){
				paramMap.put("enterpriseId", mass.getEnterpriseId());
			}
			//地块ID不为空则放入动态参数中
			if(!StringUtils.isEmpty(mass.getMassifId())){
				paramMap.put("massifId", mass.getMassifId());
			}
			//基地ID不为空则放入动态参数中
			if(!StringUtils.isEmpty(mass.getBaseId())){
				paramMap.put("baseId", mass.getBaseId());
			}
			//传入设备名称不为空则查询相关设备并返回设备序列号
			if(!StringUtils.isEmpty(deviceInfo.getDeviceName())){
				Map<String,Object> maps=new HashMap<String,Object>();
				List<String> deviceNumbers=new ArrayList<String>();
				maps.put("enterpriseId", mass.getEnterpriseId());
				maps.put("deviceName", deviceInfo.getDeviceName());
				List<DeviceInfoBean> devicelist=deviceInfoService.findByEnterpriseIdAndName(maps);
				//设备存在则放入集合中不存在定一个UUID表面查询结果为空
				if(devicelist.size()>0){
					for(int k=0;k<devicelist.size();k++){
						deviceNumbers.add(devicelist.get(k).getSerialNumber());
					}
				}else{
					deviceNumbers.add(BaseUtil.getUUID());
				}
				paramMap.put("deviceId", deviceNumbers);
			}
			List<MassifProductbean> list=massIfProductService.getMassifAndDeviceList(paramMap);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					CommonBean common=new CommonBean();
					MassifProductbean mass=list.get(i);
					List<DeviceInfoBean> devices=mass.getDeviceInfoList();
					if(devices.size()>0){
						DeviceInfoBean device=devices.get(0);
						common.setT1(mass.getId());//设置ID
						common.setT2(device.getDeviceName());//设置采集器名称
						common.setT3(mass.getBaseName());//设置基地名称
						common.setT4(mass.getMassIfName());//设置地块名称
						List<ProbeBean> probes=device.getProbeList();
						String probetype="";
						//设置探测器类型
						if(probes.size()>0){
							for(int j=0;j<probes.size();j++){
								ProbeBean probe=probes.get(j);
								ProbeDefaultBean defaultBean=probeDefaultService.findById(probe.getProbeDefaultId());
								String defaultId=defaultBean.getId();
								//如果是风向探测器
								if(defaultId.equals("2cb099c40df94c3c9aaf32d150af08f2")){
									probetype+=probe.getSurveyName()+";";
								}else{
									probetype+=probe.getSurveyName()+":"+probe.getSurveyMin()+"-"+probe.getSurveyMax()+probe.getUnit()+";";
								}
							}
						}
						if(probetype.length()>0){
							common.setT5(probetype);
						}else{
							common.setT5("未关联任何采集器");
						}
						//设置控制器类型
						List<PowerControllerBean> powerList=device.getPowerControllerList();
						String powertype="";
						if(powerList.size()>0){
							for(int j=0;j<powerList.size();j++){
								PowerControllerBean power=powerList.get(j);
								powertype+=power.getControllerName()+";";
							}
						}
						if(powertype.length()>0){
							common.setT6(powertype.substring(0, powertype.length()-1));
						}else{
							common.setT6("未关联任何控制器");
						}
						if(getRedisTemplate().hasKey(mass.getDeviceSerialNumber())){
							common.setT7("正常");
						}else{
							common.setT7("不在线");
						}
						commons.add(common);
					}
			   }			
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("动态获取企业基地信息发生异常："+e.getMessage(),e);
		}finally{
			String str=BaseUtil.objectToJsonArray(commons);
			this.printList(commons);
		}	
		log.info("-----------动态获取企业基地信息接口结束---------");
	}

	/**
	 * 通过ID查找基地地块表
	 * @throws Exception
	 */
	public void findMassifProductById() throws Exception{
		log.info("-----------通过ID查找企业基地信息接口开始---------【apiMassifProductAction_findMassifProductById.do】");
		ApiDomain domain=new ApiDomain();
		String status="";
		try {
			MassifProductbean ms=massIfProductService.getMassifAndDeviceListById(mass.getId());
			List<ProbeDefaultBean> probeDefaults=probeDefaultService.findAll();
			Map<String,Object> map=new HashMap<String,Object>();
			if(getRedisTemplate().hasKey(ms.getDeviceSerialNumber())){
				status="正常";
			}else{
				status="不在线";
			}
			map.put("status",status);
			map.put("massifProductbean", ms);
			map.put("probeDefaults", probeDefaults);
			domain.setCode("000");
			domain.setMsg("查询成功");
			domain.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("通过ID查找企业基地信息发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}	
		log.info("-----------通过ID查找企业基地信息接口结束---------");
	}
	
	/**
	 * 根据企业ID统计每个基地下的设备数量
	 * @throws Exception
	 */
	public void CountDevice() throws Exception{
		log.info("-----------设备统计接口开始---------【apiMassifProductAction_CountDevice.do】");
		ApiDomain domain=new ApiDomain();
		List<String> baseIds=new ArrayList<String>();//基地ID
		List<String> baseNames=new ArrayList<String>();//基地名称
		List<Integer> surveyCounts=new ArrayList<Integer>();//采集器数量
		List<Integer> cameraCounts=new ArrayList<Integer>();//摄像头数量
		List<String> lngs=new ArrayList<String>();//经度
		List<String> lats=new ArrayList<String>();//纬度
		Map<Object,Object> map=new HashMap<Object,Object>();
		JSONObject result=new JSONObject();
		 JSONObject paramJson=new JSONObject();
		try{
			List<MassifProductbean> list=massIfProductService.CountDeviceNumber(mass.getEnterpriseId());//通过企业ID获取地块关联表数据
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String baseId=list.get(i).getBaseId();
					String baseName=list.get(i).getBaseName();
					int type=list.get(i).getDeviceType();
					int count=list.get(i).getCount();
					//根据基地ID判断是否存在，存在则修改，不存在则添加
					if(baseIds.contains(baseId)){
						if(type==1){
							surveyCounts.set(baseIds.indexOf(baseId),count);
						}else if(type==2){
							cameraCounts.set(baseIds.indexOf(baseId),count);
						}
					}else{
						baseIds.add(baseId);
						baseNames.add(baseName);
						if(type==1){
							surveyCounts.add(count);
							cameraCounts.add(0);
						}else if(type==2){
							surveyCounts.add(0);
							cameraCounts.add(count);
						}
					}		
				}
			     paramJson.put("baseIds", StringUtils.join(baseIds,","));
			     //通过基地ID去溯源系统查询经纬度
		         result=new HttpClientUtil().httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiEnterpriseSetAction_FindBaseById.do", paramJson, false);
		         JSONArray jsons=result.getJSONArray("data");
		         if(jsons.size()>0){
		        	 for(int i=0;i<jsons.size();i++){
		     			JSONObject json=jsons.getJSONObject(i);
		     			lngs.add((String) json.get("lng"));
		     			lats.add((String) json.get("lat"));
		     		} 
		         }
				map.put("baseNames", baseNames);
				map.put("surveyCounts", surveyCounts);
				map.put("cameraCounts", cameraCounts);
				map.put("lngs", lngs);
				map.put("lats", lats);
				domain.setCode("000");
				domain.setData(map);
			}else{
				domain.setCode("001");
				domain.setData(map);
			}
		}catch(Exception e){
			e.printStackTrace();
			domain.setCode("002");
			domain.setMsg("系统异常");
			log.error("设备统计发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}
		log.info("-----------设备统计接口结束---------");
	}
	
	/**
	 * 给设备绑定基地地块
	 * @throws Exception 
	 */
	public void bindDevice() throws Exception {
		log.info("-----------给设备绑定基地地块开始---------【apiMassifProductAction_bindDevice.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String json = getRequest().getParameter("json");
			System.out.println(json);
			JSONObject jObject = JSONObject.fromObject(json);
			int status = jObject.getInt("status");
			MassifProductbean massifProductbean = null;
			if (status==0) {//绑定
				massifProductbean=new MassifProductbean();
				massifProductbean.setBaseId(jObject.getString("baseId"));
				massifProductbean.setBaseName(jObject.getString("baseName"));
				massifProductbean.setDeviceSerialNumber(jObject.getString("deviceId"));
				massifProductbean.setDeviceType(jObject.getInt("deviceType"));
				massifProductbean.setEnterpriseId(jObject.getString("enterpriseId"));
				massifProductbean.setEnterpriseName(jObject.getString("enterpriseName"));
				massifProductbean.setMassifId(jObject.getString("massifId"));
				massifProductbean.setMassIfName(jObject.getString("massifName"));
				massifProductbean.setId(BaseUtil.getUUID());
				this.massIfProductService.addMassifProduct(massifProductbean);
			}else if (status==1){//取消绑定
				this.massIfProductService.deleteMPByDeviceSer(jObject.getString("deviceId"));
			}else if (status==2){//转移绑定
				massifProductbean = this.massIfProductService.getMassifAndProbeDataByDeviceSer(jObject.getString("deviceId"));
				massifProductbean.setUploadTime(BaseUtil.getCurrentTimestr());
				massifProductbean.setBaseId(jObject.getString("baseId"));
				massifProductbean.setBaseName(jObject.getString("baseName"));
				massifProductbean.setDeviceSerialNumber(jObject.getString("deviceId"));
				massifProductbean.setDeviceType(jObject.getInt("deviceType"));
				massifProductbean.setEnterpriseId(jObject.getString("enterpriseId"));
				massifProductbean.setEnterpriseName(jObject.getString("enterpriseName"));
				massifProductbean.setMassifId(jObject.getString("massifId"));
				massifProductbean.setMassIfName(jObject.getString("massifName"));
				this.massIfProductService.updateMP(massifProductbean);
			}
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("给设备绑定基地地块发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("-----------给设备绑定基地地块结束---------");
	}
	

	/**
	 * 根据设备号获取设备
	 * @throws Exception 
	 */
	public void findBySer() throws Exception {
		log.info("-----------根据设备号获取设备开始---------【apiMassifProductAction_findBySer.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String serString = getRequest().getParameter("deviceId");
			MassifProductbean massifProductbean = this.massIfProductService.getMassifAndProbeDataByDeviceSer(serString);
			domain.setData(massifProductbean);
		} catch (Exception e) {
			// TODO: handle exception
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
			log.error("根据设备号获取设备发生异常："+e.getMessage(),e);
		}finally{
			this.printObject(domain);
		}
		log.info("-----------根据设备号获取设备结束---------");
	}
	
	public DeviceInfoBean getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfoBean deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public MassifProductbean getMass() {
		return mass;
	}

	public void setMass(MassifProductbean mass) {
		this.mass = mass;
	}
	
}
