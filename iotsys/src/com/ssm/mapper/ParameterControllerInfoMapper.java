package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.ParameterControllerInfoBean;

public interface ParameterControllerInfoMapper {
	
//	根据控制器ID返回所有参数控制信息
//	 测试运行通过 
	public List<ParameterControllerInfoBean> selectByOutKey() throws Exception;

//	增加新数据
	public void addParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean) throws Exception ;
	
//	更新数据
	public void updateParameterControllerInfo(ParameterControllerInfoBean parameterControllerInfoBean) throws Exception;
	
//	删除数据
	public void deleteParameterControllerInfoById(@Param("id")String id)throws Exception;     //根据ID删除
	
	public ParameterControllerInfoBean getParameterControllerInfoById(@Param("id")String id) throws Exception;
	
}
