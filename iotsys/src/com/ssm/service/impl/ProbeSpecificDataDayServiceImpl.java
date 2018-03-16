package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.mapper.ProbeSpecificDataDayMapper;
import com.ssm.mapper.ProbeSpecificDataHourMapper;
import com.ssm.mapper.ProbeSpecificMapper;
import com.ssm.service.IProbeSpecificDataDayService;
import com.ssm.service.IProbeSpecificDataHourService;

@Service("probeSpecificDataDayService")
public class ProbeSpecificDataDayServiceImpl  implements IProbeSpecificDataDayService{
	
	   @Resource
	   private ProbeSpecificDataHourMapper probeSpecificDataHourMapper;
	   @Resource
	   private ProbeSpecificDataDayMapper probeSpecificDataDayMapper;
	
	
	@Override
	public int addProbeSpecificDataDay(List<ProbeSpecificDataDayBean> list) throws Exception{
	    probeSpecificDataDayMapper.addProbeSpecificDataDay(list);
		return 0;
	}
	 
	@Override
	public List<ProbeSpecificDataDayBean> AvgProbeSpecificDartaByMonth(HashMap<Object, Object> params)throws Exception{
		return probeSpecificDataDayMapper.AvgProbeSpecificDartaByMonth(params);
	}
	
}
