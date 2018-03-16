package com.ssm.service;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.beans.ProbeSpecificDataMonthBean;

public interface IProbeSpecificDataMonthService {
	
	 /**
	 * 批量添加按月进行批处理统计后的数据
	 * @param list 对象集
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public int addProbeSpecificDataMonth(List<ProbeSpecificDataMonthBean> list) throws Exception;
}
