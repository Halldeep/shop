package com.oracle.maket.control;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.maket.model.dao.OrderDAO;
import com.oracle.maket.model.javaben.Detailorders;
import com.oracle.maket.model.javaben.Goods;
import com.oracle.maket.model.javaben.Orders;
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
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpSession session,Model m){
		if(session.getAttribute("logineduser")==null){
			//如果未登陆，跳转到登录页面
			return "login";
		}else{
			//如果登陆了，则查询数据库将订单详情查询好封装到一个map集合中
			Map<Orders, Map<Goods, Integer>> orderDtails= new HashMap();
			
			 List<Orders>  orders=dao.listOrders(((Users)session.getAttribute("logineduser")).getUserid());
			 
			for(Orders  o:orders){
				List<Detailorders> items=dao.listItemsByOrderId(o.getOrdersid());
				Map<Goods, Integer> itemDetails=new HashMap();
				for(Detailorders  i:items){
					Goods g=dao.getGoodsByGoodsid(i.getGoodsid());
					itemDetails.put(g, i.getGoodsnumber());
				}
				orderDtails.put(o, itemDetails);
			}
			m.addAttribute("orderDetail", orderDtails);
			return "Member-allOrder";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteProductFormShopcar(String oid,HttpSession session){
		int userid=((Users)session.getAttribute("logineduser")).getUserid();
		System.out.println("删除购物车的方法");
		int result=dao.deleteOrders(oid);
		System.out.println(result>0?"删除成功":"删除失败");
		return "redirect:list";
	}
}
