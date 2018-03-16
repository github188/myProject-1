package com.ssm.service;

import java.util.List;

import com.ssm.beans.Code;

public interface ICodeService{
	
	public Code findByTypeAddKey(String type,String Key)throws Exception;
	public List<Code> findAll()throws Exception;
	public List<Code> findByType(String type) throws Exception ;

}
