package com.ssm.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssm.util.BaseUtil;
import com.ssm.util.JsonDateValueProcessor;

public class BaseAction {
	@Autowired
	private RedisTemplate redisTemplate;
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	/** 获取URl */
	
	public String getBasePath(){
		/* 
		 http://localhost:8080/test   http://localhost:8080   http://localhost/test   http://localhost
		 */
		String str = getRequest().getScheme() + "://" + getRequest().getServerName();
		int port = getRequest().getServerPort();
		if(port != 80) {
			str += ":" + port;
		}
		String path = getRequest().getContextPath().trim();
		if(!"".equals(path) && !"/".equals(path)) {
			str += path;
		}
		
		return str;
	}
	/**
	 * 输出Object -> JSON
	 * @param o
	 * @throws Exception
	 */
	protected void printObject(Object o) throws Exception{
		getResponse().setCharacterEncoding("UTF-8");
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONObject object = JSONObject.fromObject(o,cfg);
		getResponse().setContentType("text/plain");
		getResponse().setCharacterEncoding("UTF-8");
		String callbackFunName =getRequest().getParameter("callbackparam");
		getResponse().getWriter().print(callbackFunName+"("+object.toString()+")");
	}
	
	protected void printObjectNoJsonp(Object o) throws Exception{
		getResponse().setCharacterEncoding("UTF-8");
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		JSONObject object = JSONObject.fromObject(o,cfg);
		getResponse().getWriter().print(object.toString());
	}
	protected void printObjectNoJsonps(Object o) throws Exception{
		getResponse().setCharacterEncoding("UTF-8");
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject object = JSONObject.fromObject(o,cfg);
		getResponse().getWriter().print(object.toString());
	}
	/**
	 * json过滤不需要的字段
	 * @param o
	 * @throws Exception
	 */
	protected void printObjectToAssociation(Object o,String [] field) throws Exception{
		getResponse().setCharacterEncoding("UTF-8");
		JsonConfig cfg = new JsonConfig();//建立配置文件
		cfg.setIgnoreDefaultExcludes(false);  //设置默认忽略
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		cfg.setExcludes(field);  //此处是亮点，只要将所需忽略字段
		JSONObject object = JSONObject.fromObject(o,cfg);
		getResponse().getWriter().print(object.toString());
	}
	/**
	 * 将字符串输入到前端，一般用于ajax的时候调用。
	 * @param str
	 * @throws Exception
	 */
	protected void printStr(String str) throws Exception{
		getResponse().setContentType("text/plain");
		getResponse().setCharacterEncoding("UTF-8");
		String callbackFunName =getRequest().getParameter("callbackparam");
		getResponse().getWriter().print(callbackFunName+"("+str+")");
	}
	
	protected void printStrNoJsonp(String str) throws Exception{
		getResponse().getWriter().print(str);
	}
	
	/**
	 * 直接输出 无码值转换
	 * @param list
	 * @return
	 */
	protected void printList(List list) throws Exception{
		getResponse().getWriter().print(BaseUtil.objectToJsonArray(list));
	}
	
	
	/**
	 * 码值转换输出
	 * @param list 输出list
	 * @param fields 需要码值转换的字段数组 {"ordertype","status"}
	 * @param clazz list中对象类型
	 * @return
	 * @throws Exception
	 */
	protected void printList(List list, String [] fields ,Class clazz) throws Exception{
		getResponse().getWriter().print(BaseUtil.listToJsonStr(list,fields,clazz));
	}
	
	/**
	 * 获取 HttpSession   <br /><br />
	 * 
	 * 需要注意的地方是request.getSession() 等同于 request.getSession(true)，      <br />
	 * 除非我们确认session一定存在或者sesson不存在时明确有创建session的需要，      <br />
	 * 否则尽量使用request.getSession(false)。在使用request.getSession()函数，    <br />
	 * 通常在action中检查是否有某个变量/标记存放在session中。                               <br />
	 */
	public HttpSession getSession() {
		/*
		 * HttpSession session = getRequest().getSession(boolean create);
		 * 
		 * 当前reqeust中的HttpSession，如果当前reqeust中的HttpSession 为null，
		 * 当create为true，就创建一个新的Session，否则返回null
		 */
		HttpSession session = getRequest().getSession(false);
		
		return session != null ? session : null;
	}
	
	/**
	 * 获取东哲的id
	 * @return
	 */
	public String  getDzId(){
		return System.getProperty("dz");
	}
	
	
	/** 获取项目的真实路径（绝对路径） */
	public String getRealPath(){
		return getRequest().getSession().getServletContext().getRealPath("/");
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		response.setContentType("text/html;charset=utf-8");
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return response;
	}
	public HttpServletResponse getResponseCross() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//运行请求方法跨域跨域
		response.setCharacterEncoding("UTF-8");
		return response;
	}
	public HttpServletResponse getPrintResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");
		return response;
	}
	
}
