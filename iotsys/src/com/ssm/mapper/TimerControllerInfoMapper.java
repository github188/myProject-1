package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.beans.TimerControllerInfoBean;

public interface TimerControllerInfoMapper {
	
	//根据控制器ID选择所有时间控制参数
	public List<TimerControllerInfoBean> selectByOutKey() throws Exception;
	
	//增加  (测试通过)
	public void addTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean)  throws Exception;
	
	//修改  (测试通过)
	public void updateTimerControllerInfo(TimerControllerInfoBean timerControllerInfoBean)  throws Exception;
	
	//删除  (测试通过)
	public void deleteTimerControllerInfoById(@Param("id")String id)  throws Exception;
	
	//根据ID获得Bean
	public TimerControllerInfoBean getTimerControllerInfoById(@Param("id")String id) throws Exception;
}
