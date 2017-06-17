package cn.edu.bjtu.yb.restaurant.controller.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.service.OrderService;
import cn.edu.bjtu.yb.restaurant.service.RestaurantService;

@Controller
public class WebRestaurantController {

	@Autowired
	RestaurantService service;
	@Autowired
	OrderService orderservice;

	/**
	 * <p>返回添加菜的页面
	 * @return 添加菜页面
	 */
	@GetMapping("/adddish")
	public String addDishPage() {
		return "_adddish";
	}
	
	/**
	 * 
	 * @param dishid
	 * @param windowid
	 * @param restaurantid
	 * @param model
	 * @return
	 */
	@GetMapping("/editdish")
	public String getEditPage(
			@RequestParam(value="dishid") String dishid,
			Model model){
		DishBean db = service.getDishBean(dishid);
		model.addAttribute("dish", db);
		return "html/background/EditFood";
	}
	
	@PostMapping("/editdish")
	public String updateEdit(
			@RequestParam(value="id") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") int price,
			@RequestParam(value="pic") MultipartFile file,
			@RequestParam(value="window") int window,
			HttpSession session){
		DishBean db = new DishBean();
		db.setId(id);
		db.setDescription(description);
		db.setName(name);
		db.setPrice(price);
		
		return null;
	}
	
	/**
	 * 添加菜的WEB接口，不推荐，推荐通过ajax调用REST接口
	 * @param restaurant
	 * @param name
	 * @param description
	 * @param price
	 * @param file
	 * @param window
	 * @param token
	 * @param session
	 * @return 添加菜的页面
	 */
	@PostMapping("/adddish")
	public String addDish(@PathVariable String restaurant,
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") int price,
			@RequestParam(value="pic") MultipartFile file,
			@RequestParam(value="window") int window,
			HttpSession session) {
			DishBean bean = new DishBean();
			bean.setName(name);
			bean.setDescription(description);
			bean.setPrice(price);
			bean.setWindow(window);
			bean.setRestaurant(Integer.parseInt(restaurant));
			service.addDish(bean, file);
		return "_adddish";
	}
	
	/**
	 * web端查看窗口详细信息
	 * @param restaurant 餐厅id
	 * @param window 窗口id
	 * @param httpsession
	 * @param model
	 * @return 渲染好的页面
	 */
	@GetMapping("/dishlist")
	public String getDishList(
			@RequestParam(value="restaurant") String restaurant,
			@RequestParam(value="window") String window,
			HttpSession httpsession,
			Model model){
		model.addAttribute("restaurant",httpsession.getAttribute("res"));
		model.addAttribute("windows", httpsession.getAttribute("windows"));
		List<DishBean> dlist = service.getDishListObject(restaurant, window);
		model.addAttribute("dishes", dlist);
		return "/html/background/Windows_1";
	}

}
