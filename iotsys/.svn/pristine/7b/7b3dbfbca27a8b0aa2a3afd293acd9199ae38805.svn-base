package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.mapper.ProbeSpecificDataHourMapper;
import com.ssm.mapper.ProbeSpecificMapper;
import com.ssm.service.IProbeSpecificDataHourService;

@Service("probeSpecificDataHourService")
public class ProbeSpecificDataHourServiceImpl  implements IProbeSpecificDataHourService{
	
	   @Resource
	   private ProbeSpecificMapper probeSpecificMapper;
	   @Resource
	   private ProbeSpecificDataHourMapper probeSpecificDataHourMapper;
	   
	@Override
	public int addProbeSpecificData(ProbeSpecificDataBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProbeSpecificDataBean> AvgProbeSpecificDataByHour(HashMap<Object, Object> params) throws Exception{
		return probeSpecificMapper.AvgProbeSpecificDartaByHour(params);
	}
	
	@Override
	public int addProbeSpecificDataHour(List<ProbeSpecificDataHourBean> list)throws Exception{
		probeSpecificDataHourMapper.addProbeSpecificDataHour(list);
		return 0;
	}

	@Override
	public List<ProbeSpecificDataHourBean> AvgProbeSpecificDataByDay(HashMap<Object, Object> params)throws Exception{
		return probeSpecificDataHourMapper.AvgProbeSpecificDartaByDay(params);
	}
	
}
