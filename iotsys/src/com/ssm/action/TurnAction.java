package com.ssm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

@Controller("apiTurnAction")
public class TurnAction extends BaseAction{
	
	private Logger log = Logger.getLogger(this.getClass());	
	
	public String  turnPage(){
		try{
			log.info("进入页面跳转方法");
	
			HttpServletRequest req = getRequest();
			//探测器
			String  json = req.getParameter("json");
			JSONObject jsons=JSONObject.fromObject(json);
			String enterpriseId=jsons.getString("EnterpriseId");
			String phone=jsons.getString("Phone");
			String developOrganization = jsons.getString("DevelopOrganization");
			String technicalSupport = jsons.getString("TechnicalSupport");
			Map<String,String> map=new HashMap<String,String>();
			map.put("enterpriseId", enterpriseId);
			map.put("phone", phone);
			map.put("developOrganization", developOrganization);
			map.put("technicalSupport", technicalSupport);
			HttpServletRequest request = ServletActionContext.getRequest ();
			request.setAttribute("map", map);
		}catch(Exception e){
			log.error("页面跳转发生异常："+e.getMessage(),e);
		}
		log.info("进入页面跳转方法结束");
		return "success";
	}

}
