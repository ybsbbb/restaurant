package cn.edu.bjtu.yb.restaurant.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;

public interface RestaurantService {

	public String getRestaurantList() throws IOException;
	public String getDishList(String restaurant) throws IOException;
	public int addDish(DishBean dish, MultipartFile file) throws IOException;
}
