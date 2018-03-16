package com.ssm.service;

import java.util.HashMap;
import java.util.List;

import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.ProbeSpecificDataHourBean;

public interface IProbeSpecificDataHourService {
	
	/**
	 * 添加探测数据对象
	 * @param bean 探测数据对象
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
    public int addProbeSpecificData(ProbeSpecificDataBean bean)throws Exception;	
    
    /**
	 * 按小时进行批处理统计信息
	 * @param params 动态条件信息
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public List<ProbeSpecificDataBean>  AvgProbeSpecificDataByHour(HashMap<Object,Object> params)throws Exception;
	
	 /**
		 * 批量添加按小时进行批处理统计后的数据
		 * @param list 对象集
		 * @return 
		 * @throws Exception
		 * @author zmd
		 */
	public int addProbeSpecificDataHour(List<ProbeSpecificDataHourBean> list)throws Exception;

	 /**
		 * 按天进行批处理统计信息
		 * @param params 动态条件信息
		 * @return 
		 * @throws Exception
		 * @author zmd
		 */
	public List<ProbeSpecificDataHourBean> AvgProbeSpecificDataByDay(HashMap<Object, Object> params)throws Exception;
}
