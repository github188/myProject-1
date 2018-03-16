package com.ssm.service;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;

public interface IProbeSpecificDataDayService {
	
	/**
	 * 批量插入按天进行批处理的数据
	 * @param list 对象集
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public int addProbeSpecificDataDay(List<ProbeSpecificDataDayBean> list)throws Exception;
	
	
	/**
	 * 按月进行批处理统计信息
	 * @param params 对象集
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public List<ProbeSpecificDataDayBean> AvgProbeSpecificDartaByMonth(HashMap<Object, Object> params)throws Exception;
}
