package com.ssm.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataHourBean;

public interface ProbeSpecificDataHourMapper {

	public void addProbeSpecificDataHour(List<ProbeSpecificDataHourBean> probeSpecificDataHourBeanList) throws Exception;	
	
	public List<ProbeSpecificDataHourBean>  AvgProbeSpecificDartaByDay(HashMap<Object,Object> params) throws Exception;
}
