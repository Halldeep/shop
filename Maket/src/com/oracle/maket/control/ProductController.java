package com.oracle.maket.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.maket.model.dao.ProductDAO;
import com.oracle.maket.model.javaben.Goods;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductDAO dao;
	
	@RequestMapping("/list")
	public String listProduct(Model  m){
		System.out.println("����");
		List<Goods>  gs=dao.listGoods();
		m.addAttribute("gs", gs);//����̨dao��ѯ������һ�������������Ʒ��Ϣ�洢��һ��������
		return "list";
	}
}
