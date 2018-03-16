package com.ssm.util;

import com.ssm.beans.ApiDomain;

public class ResultUtil {
	
	public static ApiDomain success(Object object){
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("成功");
		domain.setData(object);
		return domain;
	}
	
	public static ApiDomain success(){
		return success(null);
	}
	
	public static ApiDomain success(String code,Object obj){
		ApiDomain domain=new ApiDomain();
		domain.setCode(code);
		domain.setMsg("成功");
		domain.setData(obj);
		return domain;
	}
	
	public static ApiDomain success(String code,String msg,Object obj){
		ApiDomain domain=new ApiDomain();
		domain.setCode(code);
		domain.setMsg(msg);
		domain.setData(obj);
		return domain;
	}
	
	public static ApiDomain error(String code,String msg){
		ApiDomain domain=new ApiDomain();
		domain.setCode(code);
		domain.setMsg(msg);
		return domain;
	}
}
