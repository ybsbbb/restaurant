package cn.edu.bjtu.yb.restaurant.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.service.OrderService;

@Controller
public class WebOrderController {
	
	@Autowired
	OrderService orderservice;
	
	@GetMapping("/orderlist")
	public String getOrderList(
			@RequestParam(value="restaurant") String restaurant,
			@RequestParam(value="state") int state,
			HttpSession httpsession,
			Model model){
		model.addAttribute("restaurant",httpsession.getAttribute("res"));
		model.addAttribute("windows", httpsession.getAttribute("windows"));
		List<DishOrder> list = orderservice.getOrdersByRestaurantAndState(restaurant, state);
		model.addAttribute("orders", list);
		return "/html/background/Open_orders";
	}
	
	@GetMapping("/orderdetail")
	public String getOrderDetail(
			@RequestParam(value="id") String id,
			HttpSession httpsession,
			Model model){
		return "/html/background/Order_detail";
	}
	
}
