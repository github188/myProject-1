/**
 * com.dz.base.filter.AuthorityFilter
 * 创建人： sy@东哲科技
 * 创建时间： 2016/1/20
 */
package com.ssm.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 权限过滤器，必须从登录界面开始操作
 * @author sy
 * 
 */
public class AuthorityFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String url = request.getServletPath();
		//登录页面不检查session
		if(StringUtils.equals("/login.jsp", url) || 
				StringUtils.equals("/loginAction_login.do", url)||
				StringUtils.equals("/map-d.jsp", url)||
				StringUtils.equals("/vcode.jsp", url) ||
				StringUtils.equals("/findPwd.jsp", url) || 
				StringUtils.equals("/journal/jsp/simplifyLogs.jsp", url) || 
				StringUtils.equals("/agriculturalInput/jsp/warnInputsUse.jsp", url) || 
				StringUtils.equals("/system/jsp/traceabilityInformation.jsp", url) || 
				StringUtils.equals("/system/jsp/onlineRetailersTrace.jsp", url) || 
				StringUtils.equals("/system/jsp/traceaForBatch.jsp", url) || 
				StringUtils.equals("/system/jsp/traceaForMateria.jsp", url) || 
				StringUtils.equals("/system/jsp/androidTraceaForMateria.jsp", url) || 
				StringUtils.equals("/system/jsp/traceaForGauging.jsp", url) || 
				StringUtils.contains("/system/jsp/androidTraceabilityInformation2.jsp", url) ||//http://trace.dongzhetimes.com:82/?t=9oBJUYjXic4mhG2VGff 
				StringUtils.equals("/system/jsp/traceabilityInformation2.jsp", url)){
			chain.doFilter(req, response);
			return;
		}
		//app接口和打印接口、支付接口可以直接访问
		if(StringUtils.contains(url,"android") || StringUtils.contains(url,"print") 
				|| StringUtils.contains(url,"api")){
			chain.doFilter(req, response);
			return;
		}
		//获取session
		HttpSession session = request.getSession();
		
		//遍历session里面的值
		/*Enumeration e1 = session.getAttributeNames();
		while( e1.hasMoreElements()) {
			//获取名字
			String sessionName=(String)e1.nextElement();
			System.out.println("\nsession item name="+sessionName);
			System.out.println("\nsession item value="+session.getAttribute(sessionName));
		}*/
		
		//获取登录人
		/*SysUser loginUser = (SysUser) session.getAttribute("loginUser");
		
		if(loginUser == null){
			//如果session失效了就得返回登录界面重新登录
			session.removeAttribute("VCODE");
			RequestDispatcher rd = request.getRequestDispatcher("/invalid.jsp");
			rd.forward(request, response);
		}else{
			try {
				//获取上下文
			
				
				//如果session没有失效，但是在此期间被禁用了就得退出登录
				if(!userService.isOpen(loginUser.getId())){
					RequestDispatcher rd = request.getRequestDispatcher("/invalid.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				System.out.println("authorityFilter出现异常。。。");
				e.printStackTrace();
			}
		}*/
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("权限检查Filter加载成功...");
	}
}
