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
	public String getAllRestaurants() throws IOException{
		String result = service.getRestaurantList();
		return result;		
	}
	
	/**
	 * 
	 * @param restaurant 餐厅id
	 * @return 返回指定餐厅的所有菜的列表
	 * @throws IOException
	 */
	@RequestMapping(value = "/{restaurant}", method = RequestMethod.GET)
	public String getRestaurant(@PathVariable String restaurant) throws IOException{
		String result = service.getDishList(restaurant);
		return result;
	}
	
	@RequestMapping(value = "/{restaurant}", method = RequestMethod.POST)
	public String addDish(@PathVariable String restaurant,
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@RequestParam(value="price") int price,
			@RequestParam(value="pic") MultipartFile file,
			@CookieValue(value="token", required=false) String token,
			HttpSession session) throws IOException {
		if(token != null) {
			if(token.equals((String)session.getAttribute("token"))) {
				DishBean bean = new DishBean();
				bean.setName(name);
				bean.setDescription(description);
				bean.setPrice(price);
				bean.setBelongto(Integer.parseInt(restaurant));
				service.addDish(bean, file);
				return "success";
			}
		}
		return "failure";
	}
}
