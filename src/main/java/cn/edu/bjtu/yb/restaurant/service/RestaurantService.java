package cn.edu.bjtu.yb.restaurant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;

public interface RestaurantService {

	public String getRestaurantList();
	public String getDishListJSON(String restaurant, String window);
	public String getWindowListJSON(String restaurant);
	public List<WindowBean> getWindowListObject(String restaurant);
	public int addDish(DishBean dish, MultipartFile file);
	public List<DishBean> getDishListObject(String string, String string2);
}
