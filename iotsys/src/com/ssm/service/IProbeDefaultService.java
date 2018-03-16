package com.ssm.service;

import java.util.List;

import com.ssm.beans.ProbeDefaultBean;

public interface IProbeDefaultService{
	
	public List<ProbeDefaultBean> findAll()throws Exception;
	public ProbeDefaultBean findById(String id) throws Exception;

}
