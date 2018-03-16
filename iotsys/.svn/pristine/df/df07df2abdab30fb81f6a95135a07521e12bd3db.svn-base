package com.ssm.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.ProbeDefaultBean;
import com.ssm.service.impl.ProbeDefaultServiceImpl;

@Controller("apiProbeDefaultAction")
public class ProbeDefaultAction extends BaseAction{
	
	@Resource
	private ProbeDefaultServiceImpl probeDefaultService;
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	
	public void findById() throws Exception{
		log.info("-----------获取探测器类型信息接口开始---------【apiProbeDefaultAction_findById.do】");
		ApiDomain domain=new ApiDomain();
		ProbeDefaultBean probeDefault=new ProbeDefaultBean();
		try{
			String id=getRequest().getParameter("id");
			probeDefault=probeDefaultService.findById(id);
			domain.setData(probeDefault);
		}catch(Exception e){
			e.printStackTrace();
			log.error("获取探测器类型信息发生异常："+e.getMessage(),e);
		}finally{
				this.printObjectNoJsonp(domain);
		}		
		log.info("-----------获取探测器类型信息接口结束--------------");
	}
	
	public void findAll() throws Exception{
		log.info("-----------获取探测器类型全部信息接口开始---------【apiProbeDefaultAction_findAll.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("查询成功");
		try{
			List<ProbeDefaultBean> probeDefault=new ArrayList<ProbeDefaultBean>();
			probeDefault=probeDefaultService.findAll();
			domain.setData(probeDefault);
		}catch(Exception e){
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
			log.error("获取探测器类型全部信息接口发生异常："+e.getMessage(),e);
		}finally{
			this.printObjectNoJsonp(domain);
		}		
		log.info("-----------获取探测器类型全部信息接口结束--------------");
	}

}
