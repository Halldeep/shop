package com.oracle.maket.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.oracle.maket.model.javaben.Carts;
import com.oracle.maket.model.javaben.Detailorders;
import com.oracle.maket.model.javaben.Goods;
import com.oracle.maket.model.javaben.Orders;

@Mapper
public interface OrderDAO {

	@Insert("insert into orders(ordersid,orderstime,userid,state,location,notice,name) values(#{orderid},#{times},#{userid},#{status},#{address},#{remark},#{name})")
	public int addOrder(@Param("orderid")String orderid,@Param("userid")int userid,@Param("name")String name,@Param("address")String address,@Param("remark")String remark,@Param("status")String status,@Param("times")String times);

	@Insert("insert into detailorders(goodsid,goodsnumber,ordersid) values(#{goodsid},#{goodsnumber},#{orderid})")
	public int addOrderItem(@Param("orderid")String orderid,@Param("goodsid")int goodsid,@Param("goodsnumber")int goodsnumber);
	
	@Select("select *  from orders where userid=#{0}")
	public List<Orders> listOrders(int userid);
	
	@Select("select *  from detailorders where ordersid=#{0}")
	public List<Detailorders>  listItemsByOrderId(String ordersid);
	
	@Select("select * from goods where goodsid=#{0}")
	public Goods getGoodsByGoodsid(int goodsid);
	
}
