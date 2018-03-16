package com.ssm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.ssm.util.BaseUtil;
/**
 * 自定义过滤器
 * 继承struts2默认过滤器
 * @author sy
 *
 */
public class UeditorFilter extends StrutsPrepareAndExecuteFilter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();   
		String redirectPath ="/myMongo3/login.jsp";// 没有登陆转向页面
		if (url.contains("/ueditor/jsp/")) {             
			chain.doFilter(req, res);         
		}else if(url.contains("/javax.faces.resource.../WEB-INF/web.xml.jsf")){
			HttpServletResponse resp=(HttpServletResponse) res;
			resp.sendRedirect(redirectPath);
		}else{
			super.doFilter(req, res, chain);         
		} 
	}
	
	
}

