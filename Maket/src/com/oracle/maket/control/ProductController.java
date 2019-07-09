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
	public String listProduct(Model  m,int page){
		System.out.println("����");
		int count = 20;
		List<Goods>  gs=dao.listGoods((page-1)*20,20);
		m.addAttribute("gs", gs);//����̨dao��ѯ������һ�������������Ʒ��Ϣ�洢��һ��������
		
		int allCount=dao.getAllCountOfGoods();
		int allPage=allCount%count==0?allCount/count:allCount/count+1;
		
		int perviousPage = page - 1==0?1:page-1;
		int nextPage = page==allPage?allPage:page + 1;
		m.addAttribute("perviousPage", perviousPage);
		m.addAttribute("nextPage", nextPage);
		m.addAttribute("allPage", allPage);
		m.addAttribute("nowPage",page);
		m.addAttribute("allCount", allCount);
		m.addAttribute("count", count);
		return "list";
	}
}
