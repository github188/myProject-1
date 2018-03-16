package com.ssm.service;

import java.util.List;
import java.util.Map;

import com.ssm.beans.CommonBean;
import com.ssm.beans.ProbeBean;

public interface IProbeService {
	
	public List<ProbeBean>  getProbeAndProbeData(Map<String,String> params) throws Exception;

	/**
	 * 批量插入
	 * @param probeBeans
	 * @throws Exception
	 */
	public void insertMany(List<ProbeBean> probeBeans) throws Exception;

	/**
	 * 根据采集器序列号获取探测器
	 * @param serialNumber
	 * @return
	 * @throws Exception
	 */
	public List<ProbeBean> findByDeviceSerialNumber(String serialNumber) throws Exception;
	
	/**
	 * 批量更新探测器数据
	 * @param list 探测器集
	 * @return 
	 * @throws Exception
	 * @author zmd
	 */
	public void updateBatch(List<ProbeBean> list)throws Exception;
	/**
	 * 批量更新
	 * @param probeBeans
	 * @throws Exception
	 */
	public void updateProbe(ProbeBean probeBeans) throws Exception;
	
	/**
	 * 探头数据统计，页面饼图使用 (根据时间查询不同的表)
	 * @author mmy
	 * @creatTime 2018年2月6日 下午1:44:05
	 * @param probeBeans
	 * @return
	 * @throws Exception
	 */
	public 	CommonBean  getSpecificData(Map<String,String> probeBeans)throws Exception;
	/**
	 * 探头数据统计，页面饼图使用
	 * @author mmy
	 * @creatTime 2018年1月29日 上午9:33:27
	 * @param probeBeans
	 * @return 
	 * @throws Exception
	 */
	public 	CommonBean  getSpecificDataCountForPie(Map<String,String> probeBeans)throws Exception;

	public void deleteByDeviceSer(String serialNumber)throws Exception;

	public void deleteByIds(List<String> deleteIds) throws Exception;
	
}
