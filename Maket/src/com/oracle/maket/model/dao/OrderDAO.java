package com.oracle.maket.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {

	@Insert("insert into orders(ordersid,orderstime,userid,state,location,notice,name) values(#{orderid},#{times},#{userid},#{status},#{address},#{remark},#{name})")
	public int addOrder(@Param("orderid")String orderid,@Param("userid")int userid,@Param("name")String name,@Param("address")String address,@Param("remark")String remark,@Param("status")String status,@Param("times")String times);

	@Insert("insert into detailorders(goodsid,goodsnumber,ordersid) values(#{goodsid},#{goodsnumber},#{orderid})")
	public int addOrderItem(@Param("orderid")String orderid,@Param("goodsid")int goodsid,@Param("goodsnumber")int goodsnumber);

}
