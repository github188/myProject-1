package com.ssm.test;


import java.util.Date;



import com.ssm.util.QuartzCornUtils;

public class OtherTest {

	public static void main(String[] args) {
		
		Date date=new Date();
		System.out.println(date);
		System.out.println(QuartzCornUtils.getCron(date));
	}
}
