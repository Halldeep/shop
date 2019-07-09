package com.oracle.maket.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.oracle.maket.model.javaben.Carts;
import com.oracle.maket.model.javaben.Goods;



/**
 * 购物车的dao类
 * @author Administrator
 *
 */
@Mapper
public interface CartDAO {

	@Insert("insert into carts(goodsid,cartgoodsnumber,userid) values(#{productid},1,#{userid})")
	public int addProduct(@Param("userid")int userid,@Param("productid")int productid);
	
	@Select("select *  from carts where userid=#{0}")
	public List<Carts> listCartsByUserId(int userid);
	
	
	@Select("select * from goods where goodsid=#{0}")
	public  Goods  getGoodsByGoodsId(int goodsid);
	
	@Delete("delete from carts where goodsid=#{productid} and userid=#{userid}")
	public int deleteGoodsFromShopcar(@Param("userid")int userid,@Param("productid")int productid);
}
