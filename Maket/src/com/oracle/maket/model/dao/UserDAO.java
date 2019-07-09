package com.oracle.maket.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.oracle.maket.model.javaben.Users;


/**
 * 定一个userdao接口，用来给用户模块提供数据库操作的方法
 * @author Administrator
 *
 */

@Mapper
public interface UserDAO {

	@Select("select * from users where username=#{username} and password=#{password}")
	public Users login(@Param("username")String username,@Param("password")String password);

	public int addUser(String username, String nickname,String password);
	
	@Insert("insert into users(username,nickname,password) values(#{username},#{nickname},#{password})")
	public int adduser(@Param("username")String username,@Param("nickname")String nickname,@Param("password")String password);
}

