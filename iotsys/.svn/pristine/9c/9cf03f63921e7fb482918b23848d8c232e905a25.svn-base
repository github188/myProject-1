package com.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataDayBean;
import com.ssm.beans.ProbeSpecificDataHourBean;
import com.ssm.beans.ProbeSpecificDataMonthBean;
import com.ssm.mapper.ProbeSpecificDataDayMapper;
import com.ssm.mapper.ProbeSpecificDataHourMapper;
import com.ssm.mapper.ProbeSpecificDataMonthMapper;
import com.ssm.mapper.ProbeSpecificMapper;
import com.ssm.service.IProbeSpecificDataDayService;
import com.ssm.service.IProbeSpecificDataHourService;
import com.ssm.service.IProbeSpecificDataMonthService;

@Service("probeSpecificDataMonthService")
public class ProbeSpecificDataMonthServiceImpl  implements IProbeSpecificDataMonthService{

	   @Resource
	   private ProbeSpecificDataMonthMapper probeSpecificDataMonthMapper;
	   
	   @Override
	   public int addProbeSpecificDataMonth(List<ProbeSpecificDataMonthBean> list) throws Exception{
	        probeSpecificDataMonthMapper.addProbeSpecificDataMonth(list);
		     return 0;
	    }
}
