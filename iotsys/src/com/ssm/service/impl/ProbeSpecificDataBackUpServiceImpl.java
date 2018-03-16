package com.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ssm.beans.Code;
import com.ssm.beans.ProbeSpecificDataBackUpBean;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.UserBean;
import com.ssm.mapper.ProbeSpecificDataBackUpsMapper;
import com.ssm.mapper.ProbeSpecificDataMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.service.ILoginService;
import com.ssm.service.IProbeSpecificDataBackUpService;
import com.ssm.service.IProbeSpecificDataService;

@Service("probeSpecificDataBackUpService")
public class ProbeSpecificDataBackUpServiceImpl implements IProbeSpecificDataBackUpService{
	
	@Resource
	private ProbeSpecificDataBackUpsMapper probeSpecificDataBackUpsMapper;

	@Override
	public int BackUpProbeSpecificData(HashMap<Object,Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		probeSpecificDataBackUpsMapper.BackUpProbeSpecificData(params);
		return 0;
	}
}
