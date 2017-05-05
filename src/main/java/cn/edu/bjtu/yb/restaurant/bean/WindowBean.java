package cn.edu.bjtu.yb.restaurant.bean;

import java.util.List;

/**
 * <p>窗口模型
 * <p>dishes是该窗口菜的列表,此属性在查询数据库时不会进行关联查询,如需使用,需要自行查询，在服务器端完成注入
 * @author yb775
 *
 */
public class WindowBean {
	private int id;
	private int restaurant;
	private String name;
	private List<DishBean> dishes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DishBean> getDishes() {
		return dishes;
	}
	public void setDishes(List<DishBean> dishes) {
		this.dishes = dishes;
	}
}
