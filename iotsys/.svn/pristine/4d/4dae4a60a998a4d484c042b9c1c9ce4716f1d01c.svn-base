package com.ssm.action;
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
import com.ssm.beans.UserBean;
import com.ssm.service.ILoginService;

@Scope("prototype")
public class RedisAction extends BaseAction {
	@SuppressWarnings("rawtypes")
	@Resource
	private ILoginService loginService;
	
	private Logger log = Logger.getLogger(this.getClass());		
	/**
	 * 测试接口方法
	 */
	public void findAllUser()throws Exception{
		log.info("-----测试接口---【apiAlQaedaAction_operateAlQaeda.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try{
			 //添加一个 key 值
			HashOperations<String, Object, Object>  hash = getRedisTemplate().opsForHash();
		    Map<String,Object> map = new HashMap<String,Object>();
	        map.put("name", "lp");
	        map.put("age", "26");
	        hash.putAll("lpMap222222222222", map);
	    	List<UserBean> list=this.loginService.findAllUser("0");
			domain.setData(list);
		}catch(Exception e){
			e.printStackTrace();
			domain.setCode("001");
			domain.setMsg("系统异常");
			log.error("添加修改基地发生异常："+e.getMessage(),e);
		}finally{
		
			this.printObjectNoJsonp(domain);
		}
		log.info("-----测试接口结束----");
	}
}
