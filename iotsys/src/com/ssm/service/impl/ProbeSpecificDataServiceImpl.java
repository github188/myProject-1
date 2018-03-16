package com.ssm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ssm.beans.Code;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.UserBean;
import com.ssm.mapper.ProbeSpecificDataMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.service.ILoginService;
import com.ssm.service.IProbeSpecificDataService;

@Service("probeSpecificDataService")
public class ProbeSpecificDataServiceImpl implements IProbeSpecificDataService{
	
	@Resource
	private ProbeSpecificDataMapper um;

	@Override
	public List<ProbeSpecificDataBean> findNewData(Map<String,String> paramMap) {
		return um.findNewData(paramMap);
	}
	@Override
	public List<ProbeSpecificDataBean> queryEnvirDataCount(
			Map<String, Object> paramMap) {
		return um.queryEnvirDataCount(paramMap);
	}
	@Override
	public List<ProbeSpecificDataBean> queryEnvirDataCountHour(
			Map<String, Object> paramMap) {
		return um.queryEnvirDataCountHour(paramMap);
	}

	@Override
	public List<ProbeSpecificDataBean> queryEnvirDataCountDay(
			Map<String, Object> paramMap) {
		return um.queryEnvirDataCountDay(paramMap);
	}

	@Override
	public List<ProbeSpecificDataBean> queryEnvirDataCountMonth(
			Map<String, Object> paramMap) {
		return um.queryEnvirDataCountMonth(paramMap);
	}

	@Override
	public String queryEnvirDataAvg(Map<String, Object> paramMap) {
		return um.queryEnvirDataAvg(paramMap);
	}
	
	@Override
	public String queryEnvirDataHourAvg(Map<String, Object> paramMap) {
		return um.queryEnvirDataHourAvg(paramMap);
	}

	@Override
	public String queryEnvirDataDayAvg(Map<String, Object> paramMap) {
		return um.queryEnvirDataDayAvg(paramMap);
	}

	@Override
	public String queryEnvirDataMonthAvg(Map<String, Object> paramMap) {
		return um.queryEnvirDataMonthAvg(paramMap);
	}
	@Override
	public List<ProbeSpecificDataBean> FindDataByTime(
			Map<Object, Object> paramMap) {
		// TODO Auto-generated method stub
		return um.FindDataByTime(paramMap);
	}

	
}
