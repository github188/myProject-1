package com.ssm.service;


import java.util.List;

import com.ssm.beans.UserBean;

public interface ILoginService {

	public UserBean Login(String username,String password);
	
	public List<UserBean> findAllUser(String id);
	
	public void delStudent(String stId);
}
