package com.ssm.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.beans.ProbeSpecificDataMonthBean;

public interface ProbeSpecificDataMonthMapper {

	public void addProbeSpecificDataMonth(List<ProbeSpecificDataMonthBean> probeSpecificDataMonthBeanList) throws Exception;	
	
}
