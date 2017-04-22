package cn.edu.bjtu.yb.restaurant.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.service.RestaurantService;

@Controller
public class WebRestaurantController {

	@Autowired
	RestaurantService service;
	
	@GetMapping("/adddish")
	public String addDishPage() {
		return "_adddish";
	}
	
	@PostMapping("/adddish")
	public String addDish(@PathVariable String restaurant,
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") int price,
			@RequestParam(value="pic") MultipartFile file,
			@RequestParam(value="window") int window,
			@CookieValue(value="token", required=false) String token,
			HttpSession session) throws IOException {
		if(token != null) {
			if(token.equals((String)session.getAttribute("token"))) {
				DishBean bean = new DishBean();
				bean.setName(name);
				bean.setDescription(description);
				bean.setPrice(price);
				bean.setWindow(window);
				bean.setRestaurant(Integer.parseInt(restaurant));
				service.addDish(bean, file);
			}
		}
		return "_adddish";
	}
}
