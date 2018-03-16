package com.ssm.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.util.BaseUtil;

public class DayEndProcess {
	protected final Logger log = LoggerFactory
			.getLogger(this.getClass());
	
	/**
	 * @throws Exception 
	 */
	public void emptyYestodayIntegral() throws Exception{
		System.out.println("日钟处理-------------------------");
	}
}
