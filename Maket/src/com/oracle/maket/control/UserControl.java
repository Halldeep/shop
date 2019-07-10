package com.oracle.maket.control;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.maket.model.dao.ProductDAO;
import com.oracle.maket.model.dao.UserDAO;
import com.oracle.maket.model.javaben.Users;



@Controller
@RequestMapping("/user")
public class UserControl {
	
	@Autowired
	private UserDAO dao;
	@RequestMapping("/login")
	public String login(String username,String password,HttpSession  session) {
		System.out.println("user -login");
		
		//1.获取用户在表单上填写的账户资料
		System.out.println(username+"\t"+password);
		
		//2.查询数据库是否存在这个对应的账户和密码
		Users  u=dao.login(username, password);
		
		System.out.println(u);
		
		//3.判断，如果存在则跳转到首页，否则跳回到登录
		if(u==null){
			System.out.println("login fail");
			return "login";
		}else{
			System.out.println("login success");
			//应该讲登录成功的用户资料存储在session，这样页面可以访问登陆后的用户信息
			session.setAttribute("logineduser", u);
			return "list";
		}
		
	}
	@RequestMapping("/register")
	public  String addUser(String username,String nickname,String password){
		//1.获取提交订单页面上的各种参数（购买的商品编号和对应的数量，收货人的信息和备注）

		System.out.println(username);
		System.out.println(nickname);
		System.out.println(password);
	
		
		int result1=dao.selectuser(username);
		if(result1!=0)
		{
			JOptionPane.showMessageDialog(null, "用户已存在", "alert", JOptionPane.ERROR_MESSAGE);
			return "register";
		}
		else if(result1==0)
		{
			int result=dao.adduser(username,nickname,password);
			JOptionPane.showMessageDialog(null, "注册成功", "alert", JOptionPane.ERROR_MESSAGE);
			
		}
		return "login";
	}
	
	
	

   @RequestMapping("/logout")
    public  String logout(HttpSession session){
	 session.setAttribute("logineduser",null);
	 return"list";
}}