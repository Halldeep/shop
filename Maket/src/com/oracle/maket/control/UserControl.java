package com.oracle.maket.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControl {
	
	@RequestMapping("/login")
	public String login(){
		System.out.println("这是");
		return "index";
	}
	@RequestMapping("/register")
	public String registert(){
		System.out.println("这是");
		return "index";
	}
}
