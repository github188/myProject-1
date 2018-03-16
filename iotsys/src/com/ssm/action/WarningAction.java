package com.ssm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Controller;


import com.ssm.service.IDeviceInfoService;
import com.ssm.util.HttpClientUtil;
import com.ssm.util.PropertiesUtil;
import com.ssm.action.BaseAction;
import com.ssm.beans.DeviceInfoBean;

@Controller("apiWarningAction")
public class WarningAction  extends BaseAction{
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	@Resource
	private IDeviceInfoService deviceInfoService;
	
	/**
	 * 预警通知
	 * @throws Exception
	 */
	public void WarningDetail() throws Exception{
		log.info("-----------预警通知接口开始---------【apiWarningAction_WarningDetail.do】");
		JSONObject result=new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		List<DeviceInfoBean> devices=new ArrayList<DeviceInfoBean>();
		HashOperations<String, Object, Object>  hash = getRedisTemplate().opsForHash();
		try{
			 JSONObject paramJson = new JSONObject();
			 String enterpriseId = getRequest().getParameter("enterpriseId");
			 String warningStatus = getRequest().getParameter("warningStatus");
			 String warningPhone = getRequest().getParameter("warningPhone");
			 String warningEmail = getRequest().getParameter("warningEmail");
			 String startTime = getRequest().getParameter("startTime");
			 String endTime = getRequest().getParameter("endTime");
			 params.put("enterpriseId", enterpriseId);
		        paramJson.put("enterpriseId", enterpriseId);
		        paramJson.put("warningStatus", warningStatus);
		        paramJson.put("warningPhone", warningPhone);
		        paramJson.put("warningEmail", warningEmail);
		        paramJson.put("startTime", startTime);
		        paramJson.put("endTime", endTime);
                result=new HttpClientUtil().httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiEnterpriseSetAction_WaringDetailInfo.do", paramJson, false);
                devices=deviceInfoService.findByCondition(params);
                if(devices!=null&&devices.size()>0){
                	if(warningStatus.equals("0")){
                		for(int i=0;i<devices.size();i++){
                			getRedisTemplate().delete("YJTZFSC"+devices.get(i).getSerialNumber());
                		}
                	}else{
                		for(int i=0;i<devices.size();i++){
                			Map<String,Object> map = new HashMap<String,Object>();
                	        map.put("phone", warningPhone);
                	        map.put("mail",warningEmail);
                	        map.put("startTime", startTime);
                	        map.put("endTime",endTime);
                	        hash.putAll("YJTZFSC"+devices.get(i).getSerialNumber(), map);
                		}
                	}
                }
               
		}catch(Exception e){
			e.printStackTrace();
			log.error("预警通知发生异常："+e.getMessage(),e);
		}finally{
				this.printObjectNoJsonp(result);
		}		
		log.info("-----------预警通知接口结束--------------");
	}

	public void FindByEnterpriseId() throws Exception{
		log.info("-----------获取企业预警信息接口开始---------【apiWarningAction_FindByEnterpriseId.do】");
		JSONObject result=new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		try{
			 JSONObject paramJson = new JSONObject();
			 String enterpriseId = getRequest().getParameter("enterpriseId");
			 paramJson.put("enterpriseId", enterpriseId);
			 result=new HttpClientUtil().httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiEnterpriseSetAction_FindByEnterpriseId.do", paramJson, false);
		}catch(Exception e){
			e.printStackTrace();
			log.error("获取企业预警信息发生异常："+e.getMessage(),e);
		}finally{
			this.printObjectNoJsonps(result);
		}
		log.info("-----------获取企业预警信息接口结束---------【apiWarningAction_FindByEnterpriseId.do】");
	}
	
}
