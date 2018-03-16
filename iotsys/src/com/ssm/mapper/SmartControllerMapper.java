package com.ssm.mapper;

import java.util.List;

import org.ietf.jgss.Oid;

import com.ssm.beans.ParameterControllerInfoBean;
import com.ssm.beans.SmartControllerBean;
import com.ssm.beans.TimerControllerInfoBean;

public interface SmartControllerMapper {
	
	//返回所有数据
	public List<SmartControllerBean> selectAll() throws Exception;
	
}
