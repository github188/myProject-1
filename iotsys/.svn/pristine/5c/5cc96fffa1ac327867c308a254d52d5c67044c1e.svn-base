package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ssm.beans.Code;
import com.ssm.beans.UserBean;
import com.ssm.mapper.UserMapper;
import com.ssm.service.ILoginService;

@Service("loginService")
public class LoginServiceImpl implements ILoginService{
	
	@Resource
	private UserMapper um;
	
	public UserMapper getUm() {
		return um;
	}
	public void setUm(UserMapper um) {
		this.um = um;
	}
	@Override
	public UserBean Login(String username, String password) {
		return um.login(username, password);
	}
	@Override
	public void delStudent(String stId) {
		um.delStudent(stId);
	}
	@Override
	public List<UserBean> findAllUser(String id) {
		return um.findAllUser(id);
	}
	
	
	public static void main(String[] args) {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext( 
        "applicationContext.xml"); 
		//获取service的bean
		CodeServiceImpl is = (CodeServiceImpl) ctx.getBean("codeService");
		try {
			List<Code> list = is.findAll();
			System.out.println(list);
//			System.out.println(BaseUtil.objectToJson(is.findAll()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
