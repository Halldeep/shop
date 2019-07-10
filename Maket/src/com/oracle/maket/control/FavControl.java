package com.oracle.maket.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.maket.model.dao.FavDAO;
import com.oracle.maket.model.javaben.Goods;
import com.oracle.maket.model.javaben.Users;

@Controller
@RequestMapping("/fav")
public class FavControl {

	@Autowired
	private FavDAO dao;
	
	@RequestMapping("/add")
	public String addToFav(int pid,HttpSession  session){
		if(session.getAttribute("logineduser")==null){
			return "login";
		}else{
			int userid=((Users)session.getAttribute("logineduser")).getUserid();
			int result=dao.addProductToFav(pid, userid, new Date().toLocaleString());
			System.out.println(result>0?"Ìí¼Ó³É¹¦":"Ìí¼ÓÊ§°Ü");
			return "redirect:list";
		}
	}
	
	@RequestMapping("/list")
	public String list(Model m,HttpSession  session){
		if(session.getAttribute("logineduser")==null){
			return "login";
		}else{
		int userid=((Users)session.getAttribute("logineduser")).getUserid();
		List<Goods> gs=dao.listAllProductsOfFav(userid);
		m.addAttribute("gs", gs);
		return "fav";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteProductFormShopfav(int pid,HttpSession session){
		int userid=((Users)session.getAttribute("logineduser")).getUserid();
		System.out.print("É¾³ıÊÕ²Ø¼Ğ");
		int result=dao.deleteProductFormShopfav(userid,pid);
		System.out.println(result>0?"É¾³ı³É¹¦":"É¾³ıÊ§°Ü");
		return "redirect:list";
	}
	
}
