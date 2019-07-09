package com.oracle.maket.control;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.maket.model.dao.OrderDAO;
import com.oracle.maket.model.javaben.Users;


@Controller
@RequestMapping("/order")
public class OrderControl {
	@Autowired
	private OrderDAO dao;

	@RequestMapping("/add")
	public  String addOrder(int[] pid,int[] count,String name,String address,String remark,HttpSession session){
		//1.获取提交订单页面上的各种参数（购买的商品编号和对应的数量，收货人的信息和备注）
		System.out.println(Arrays.toString(pid));
		System.out.println(Arrays.toString(count));
		String orderNum=UUID.randomUUID().toString();
		int result=dao.addOrder(orderNum,((Users)session.getAttribute("logineduser")).getUserid(), name, address, remark, "已付款", new Date().toLocaleString());
		
		for(int n=0;n<pid.length;n++){
			int result1 = dao.addOrderItem(orderNum,pid[n], count[n]);
		}
		System.out.println(result>0?"订单提成功":"提交失败");
		return "Member-allOrder";
	}
}
