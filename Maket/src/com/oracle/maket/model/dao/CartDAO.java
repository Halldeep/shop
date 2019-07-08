package com.oracle.maket.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDAO {
	
	public int addProduct(int userid,int productud);
}
