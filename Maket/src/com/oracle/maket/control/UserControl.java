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
		
		//1.��ȡ�û��ڱ�����д���˻�����
		System.out.println(username+"\t"+password);
		
		//2.��ѯ���ݿ��Ƿ���������Ӧ���˻�������
		Users  u=dao.login(username, password);
		
		System.out.println(u);
		
		//3.�жϣ������������ת����ҳ���������ص���¼
		if(u==null){
			System.out.println("login fail");
			return "login";
		}else{
			System.out.println("login success");
			//Ӧ�ý���¼�ɹ����û����ϴ洢��session������ҳ����Է��ʵ�½����û���Ϣ
			session.setAttribute("logineduser", u);
			return "list";
		}
		
	}
	@RequestMapping("/register")
	public  String addUser(String username,String nickname,String password){
		//1.��ȡ�ύ����ҳ���ϵĸ��ֲ������������Ʒ��źͶ�Ӧ���������ջ��˵���Ϣ�ͱ�ע��

		System.out.println(username);
		System.out.println(nickname);
		System.out.println(password);
	
		
		int result1=dao.selectuser(username);
		if(result1!=0)
		{
			JOptionPane.showMessageDialog(null, "�û��Ѵ���", "alert", JOptionPane.ERROR_MESSAGE);
			return "register";
		}
		else if(result1==0)
		{
			int result=dao.adduser(username,nickname,password);
			JOptionPane.showMessageDialog(null, "ע��ɹ�", "alert", JOptionPane.ERROR_MESSAGE);
			
		}
		return "login";
	}
	
	
	

   @RequestMapping("/logout")
    public  String logout(HttpSession session){
	 session.setAttribute("logineduser",null);
	 return"list";
}}