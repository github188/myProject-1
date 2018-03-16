package com.ssm.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.UserBean;

public interface IProbeSpecificDataService {

	/**
	 * 根据设备序号找到所有探头的最新一条数据
	 * @param dsn 设备号
	 * @return
	 */
	public List<ProbeSpecificDataBean> findNewData(Map<String,String> paramMap);
	
	public List<ProbeSpecificDataBean> queryEnvirDataCount(Map<String,Object> paramMap);
	
	public List<ProbeSpecificDataBean> queryEnvirDataCountHour(Map<String,Object> paramMap);
	
	public List<ProbeSpecificDataBean> queryEnvirDataCountDay(Map<String,Object> paramMap);
	
	public List<ProbeSpecificDataBean> queryEnvirDataCountMonth(Map<String,Object> paramMap);
	
	public String queryEnvirDataAvg(Map<String,Object> paramMap);
	
	public String queryEnvirDataHourAvg(Map<String,Object> paramMap);
	
	public String queryEnvirDataDayAvg(Map<String,Object> paramMap);
	
	public String queryEnvirDataMonthAvg(Map<String,Object> paramMap);
	
	public List<ProbeSpecificDataBean> FindDataByTime(Map<Object, Object> paramMap);
}
