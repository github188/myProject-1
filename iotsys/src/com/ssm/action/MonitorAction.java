package com.ssm.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.DeviceInfoBean;
import com.ssm.beans.MassifProductbean;
import com.ssm.beans.ProbeSpecificDataBean;
import com.ssm.beans.UserBean;
import com.ssm.service.IDeviceInfoService;
import com.ssm.service.ILoginService;
import com.ssm.service.IMassIfProductService;
import com.ssm.service.IProbeSpecificDataService;
import com.ssm.service.impl.DeviceInfoServiceImpl;
import com.ssm.service.impl.MassIfProductServiceImpl;
import com.ssm.util.BaseUtil;
import com.ssm.util.StringUtils;

/**
 * 
 * @author mmy
 * @creatTime 2018年1月22日 下午9:27:25
 */
@Scope("prototype")
public class MonitorAction extends BaseAction {
	
	private String baseId;
	private String massifId;
	private String enterpriseId;
	private String deviceName;

	/*
	 *登录相关暂时没用
	 */
	@Resource
	private ILoginService loginService;
	/*
	 *探头数据相关
	 */
	@Resource
	private IProbeSpecificDataService probeSpecificDataService;
	/*
	 *地块相关
	 */
	@Resource
	private IMassIfProductService massIfProductService;
	/*
	 * 设备相关
	 */
	@Resource
	private IDeviceInfoService deviceInfoService;
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	/**
	 * @author mmy
	 * @creatTime 2018年1月22日 下午9:30:52
	 * @throws Exception
	 */
	public void findBase()throws Exception{
		log.info("开始查找全部基地数据");
		
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try{
			Map<String, Object> paramMap=new HashMap<String, Object>();
			
			if(!StringUtils.isEmpty(enterpriseId)){//暂时没用的条件
				paramMap.put("enterpriseId", enterpriseId);
			}
			if(!StringUtils.isEmpty(baseId)){//暂时没用的条件
				paramMap.put("baseId", baseId);
			}
			List<MassifProductbean> list=massIfProductService.getBase(paramMap);
	    	domain.setData(list);
		}catch(Exception e){
			e.printStackTrace();
		
			domain.setCode("001");
			domain.setMsg("系统异常");
		}finally{
			log.info("结束查找全部基地数据");
			this.printObject(domain);
		}
	}
	public void findMassif()throws Exception{
		log.info("开始根据基地Id查找地块数据");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		
		try{
			Map<String, Object> paramMap=new HashMap<String, Object>();
			if(!StringUtils.isEmpty(baseId)){
				log.info("开始根据基地Id查找地块数据条件：baseId:"+baseId);
				paramMap.put("baseId", baseId);
			}
			List<MassifProductbean> list=massIfProductService.getMassif(baseId);
			domain.setData(list);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询地块数据出错："+e.getMessage(),e);
			domain.setCode("001");
			domain.setMsg("系统异常");
		}finally{
			log.info("结束根据基地Id查找地块数据");
			this.printObject(domain);
		}
	}
	/**
	 * 测试接口方法
	 */
	public void findProbeInfo()throws Exception{
		log.info("开始查找探头数据");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		
		try{
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			if(!StringUtils.isEmpty(enterpriseId)){
				paramMap.put("enterpriseId", enterpriseId);
			}
			if(!baseId.equals("-1")){
				paramMap.put("baseId", baseId);
			}
			if(!massifId.equals("-1")){
				paramMap.put("massifId", massifId);
			}
			
			if(!StringUtils.isEmpty(deviceName)){
				Map<String,Object> maps=new HashMap<String,Object>();
				List<String> deviceNumbers=new ArrayList<String>();
				maps.put("deviceName", deviceName);
				List<DeviceInfoBean> devicelist=deviceInfoService.findByEnterpriseIdAndName(maps);
				log.info("查找探头数据结束，开始数据整理！");
				if(devicelist.size()>0){
					for(int k=0;k<devicelist.size();k++){
						deviceNumbers.add(devicelist.get(k).getSerialNumber());
					}
				}else{
					deviceNumbers.add(BaseUtil.getUUID());
				}
				paramMap.put("deviceId", deviceNumbers);
			}
			List<MassifProductbean> list=massIfProductService.getDeviceListForMassif(paramMap);//getMassifAndDeviceList
			
			for(int x=0;x<list.size();x++){
				List<DeviceInfoBean> devlist  = list.get(x).getDeviceInfoList(); //地块下有设备的数据
				if(devlist.size()>0){
					String xxa = (String) getRedisTemplate().opsForValue().get(devlist.get(0).getSerialNumber());//getDeviceSerialNumber();
					if(xxa!=null && xxa.equals("1")){
						devlist.get(0).setId("online");
						list.get(x).setDeviceInfoList(devlist);
					}
				}
				
			}
			log.info("查找探头数据结束，结束数据整理！");
	    	domain.setData(list);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询环境数据出错："+e.getMessage(),e);
			domain.setCode("001");
			domain.setMsg("系统异常");
		}finally{
			log.info("结束查找探头数据");
			this.printObject(domain);
		}
	}
	
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getMassifId() {
		return massifId;
	}
	public void setMassifId(String massifId) {
		this.massifId = massifId;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
