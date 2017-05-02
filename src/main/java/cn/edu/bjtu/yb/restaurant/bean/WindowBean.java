package cn.edu.bjtu.yb.restaurant.bean;

import java.util.List;

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
