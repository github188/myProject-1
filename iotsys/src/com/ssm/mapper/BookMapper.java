package com.ssm.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.ssm.beans.BookBean;

public interface BookMapper {
	
	public List<BookBean> findBookForUserId(@Param("userId")String userId);	
    
}
