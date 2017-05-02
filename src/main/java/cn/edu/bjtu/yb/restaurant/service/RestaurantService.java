package cn.edu.bjtu.yb.restaurant.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;

public interface RestaurantService {

	public String getRestaurantList() throws IOException;
	public String getDishListJSON(String restaurant, String window) throws IOException;
	public String getWindowListJSON(String restaurant) throws IOException;
	public List<WindowBean> getWindowListObject(String restaurant) throws IOException;
	public int addDish(DishBean dish, MultipartFile file) throws IOException;
	public List<DishBean> getDishListObject(String string, String string2) throws IOException;
}
