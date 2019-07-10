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
		//1.��ȡ�ύ����ҳ���ϵĸ��ֲ������������Ʒ��źͶ�Ӧ���������ջ��˵���Ϣ�ͱ�ע��
		System.out.println(Arrays.toString(pid));
		System.out.println(Arrays.toString(count));
		String orderNum=UUID.randomUUID().toString();
		int result=dao.addOrder(orderNum,((Users)session.getAttribute("logineduser")).getUserid(), name, address, remark, "�Ѹ���", new Date().toLocaleString());
		
		for(int n=0;n<pid.length;n++){
			int result1 = dao.addOrderItem(orderNum,pid[n], count[n]);
		}
		System.out.println(result>0?"������ɹ�":"�ύʧ��");
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpSession session,Model m){
		if(session.getAttribute("logineduser")==null){
			//���δ��½����ת����¼ҳ��
			return "login";
		}else{
			//�����½�ˣ����ѯ���ݿ⽫���������ѯ�÷�װ��һ��map������
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
		System.out.println("ɾ�����ﳵ�ķ���");
		int result=dao.deleteOrders(oid);
		System.out.println(result>0?"ɾ���ɹ�":"ɾ��ʧ��");
		return "redirect:list";
	}
}
