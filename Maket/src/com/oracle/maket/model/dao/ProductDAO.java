package com.oracle.maket.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.oracle.maket.model.javaben.Goods;



@Mapper
public interface ProductDAO {
	@Select("select *  from goods limit #{startindex},#{count}")
	public List<Goods> listGoods(@Param("startindex")int startindex, @Param("count")int count);
	
	@Select("select count(*) from goods")
	public int getAllCountOfGoods();
}
