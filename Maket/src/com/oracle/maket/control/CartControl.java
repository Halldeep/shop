package com.oracle.maket.control;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartControl {
	
	@RequestMapping("/add")
	public String addProductToCart(int pid,HttpSession session){
		
		
		System.out.println("���Ǽ��빺�ﳵ");
		return "Cart";
	}
}
