package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.beans.ProbeDefaultBean;
import com.ssm.mapper.ProbeDefaultMapper;
import com.ssm.service.IProbeDefaultService;

@Service("probeDefaultService")
public class ProbeDefaultServiceImpl  implements IProbeDefaultService{
	@Resource
	private ProbeDefaultMapper ProbeDefaultDao;

	@Override
	public List<ProbeDefaultBean> findAll() throws Exception {
		return ProbeDefaultDao.findAll();
	}

	@Override
	public ProbeDefaultBean findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return ProbeDefaultDao.findById(id);
	}
}
