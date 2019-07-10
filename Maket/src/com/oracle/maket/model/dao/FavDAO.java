package com.oracle.maket.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.oracle.maket.model.javaben.Goods;

@Mapper
public interface FavDAO {

	@Insert("insert into favorites(goodsid,userid,jointime) values(#{goodsid},#{userid},#{datetime})")
	public int addProductToFav(@Param("goodsid")int goodsid,@Param("userid")int userid,@Param("datetime")String datetime);
	
	@Select("select *  from goods where goodsid in (select goodsid from favorites where userid=#{0})")
	public List<Goods> listAllProductsOfFav(int userid);
	
	@Delete("delete from favorites where goodsid=#{goodsid} and userid=#{userid}")
	public int deleteProductFormShopfav(@Param("userid")int userid,@Param("goodsid")int goodsid);

}

