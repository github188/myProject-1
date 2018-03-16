/**
 * com.dz.util.CodeListener
 * 创建人： sy@东哲科技
 * 创建时间： 2016/1/22
 */
package com.ssm.util;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssm.beans.Code;
import com.ssm.service.impl.CodeServiceImpl;

public class CodeListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CodeMap.codeMap = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//获取上下文对象
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		//获取userService对象
		CodeServiceImpl codeService = (CodeServiceImpl) context.getBean("codeService");
		try {
			//获取码表数据
			List<Code> list=codeService.findAll();
			for(int i=0;i<list.size();i++){
				Code code = list.get(i);
				//codetype+codekey为唯一标示符
				CodeMap.put(code.getCodetype()+code.getCodekey(), code.getCodevalue());
				System.out.println("加载码表：  "+code.getCodetype()+"   "+code.getCodekey()+"  "+code.getCodevalue());
			}
			//把东哲公司的id号保存，以便随时获取
			System.setProperty("dz", "f136cb03-82c0-4cb6-baea-bf69501618d4");
			
		} catch (Exception e) {
			System.out.println("加载码表失败！！！  修改数据库，需要更改查询的表com.ssm.util.CodeListener");
			e.printStackTrace();
		}
		
	}
}
