package cn.edu.bjtu.yb.restaurant.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;

/**
 * 有关餐厅、餐品的所有service接口
 * @author 杨博
 *
 */
public interface RestaurantService {

	/**
	 * 获取餐厅列表
	 * @return 餐厅列表JSON
	 */
	public String getRestaurantList();
	
	/**
	 * 根据窗口、餐厅获取菜列表的JSONArray
	 * @param restaurant
	 * @param window
	 * @return 菜列表的JSON字符串
	 */
	public String getDishListJSON(String restaurant, String window);
	
	/**
	 * 根据餐厅获取窗口列表的JSONArray
	 * @param restaurant
	 * @return 窗口列表的JSON字符串
	 */
	public String getWindowListJSON(String restaurant);
	
	/**
	 * 根据餐厅获取窗口列表的List
	 * @param restaurant
	 * @return
	 */
	public List<WindowBean> getWindowListObject(String restaurant);
	
	/**
	 * 添加菜信息
	 * @param dish
	 * @param file 菜的图片描述
	 * @return
	 */
	public int addDish(DishBean dish, MultipartFile file);
	
	/**
	 * 根据窗口、餐厅获取菜列表的List
	 * @param restaurant
	 * @param window
	 * @return 
	 */
	public List<DishBean> getDishListObject(String restaurant, String window);
}
