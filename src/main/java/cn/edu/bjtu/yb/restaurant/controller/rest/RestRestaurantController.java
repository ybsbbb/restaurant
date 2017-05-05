package cn.edu.bjtu.yb.restaurant.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestRestaurantController {

	@Autowired
	RestaurantService service;
	
	/**
	 * 
	 * @return 以JSON格式返回所有餐厅的列表
	 * @throws IOException
	 */
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String getAllRestaurants() {
		String result = service.getRestaurantList();
		return result;		
	}
	
	/**
	 * 
	 * @param restaurant 餐厅id
	 * @return 返回指定餐厅的所有窗口的列表
	 * @throws IOException
	 */
	@RequestMapping(value = {"/{restaurant}","/{restaurant}/windows"}, method = RequestMethod.GET)
	public String getWindowsByRestaurant(@PathVariable String restaurant) {
		String result = service.getWindowListJSON(restaurant);
		return result;
	}
	
	@RequestMapping(value = {"/{restaurant}/windows/{window}"}, method = RequestMethod.GET)
	public String getDishByRestaurantAndWindow(@PathVariable String restaurant,
			@PathVariable String window) {
		String result = service.getDishListJSON(restaurant, window);
		return result;
	}
	
	/**
	 * 添加菜
	 * @param restaurant餐厅id
	 * @param name 菜名
	 * @param description 菜的描述
	 * @param price 菜的价格
	 * @param file 菜的样图
	 * @param window 菜的窗口
	 * @param token 用户身份
	 * @param session
	 * @return 菜添加结果(success/failure)
	 * @throws IOException
	 */
	@RequestMapping(value = "/{restaurant}/windows/{window}", method = RequestMethod.POST)
	public String addDish(@PathVariable String restaurant,
			@PathVariable String window,
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") int price,
			@RequestParam(value="pic") MultipartFile file,
			@CookieValue(value="token", required=false) String token,
			HttpSession session) {
		if(token != null) {
			if(token.equals((String)session.getAttribute("token"))) {
				DishBean bean = new DishBean();
				bean.setName(name);
				bean.setDescription(description);
				bean.setPrice(price);
				bean.setWindow(Integer.parseInt(window));
				bean.setRestaurant(Integer.parseInt(restaurant));
				service.addDish(bean, file);
				return "success";
			}
		}
		return "failure";
	}
}
