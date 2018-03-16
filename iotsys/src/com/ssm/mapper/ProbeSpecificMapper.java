package com.ssm.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;

public interface ProbeSpecificMapper {

	public int addProbeSpecificData(ProbeSpecificDataBean bean) throws Exception;	
	
	public List<ProbeSpecificDataBean>  AvgProbeSpecificDartaByHour(HashMap<Object,Object> params) throws Exception;
}
