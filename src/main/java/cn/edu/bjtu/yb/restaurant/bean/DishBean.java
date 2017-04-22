package cn.edu.bjtu.yb.restaurant.bean;

public class DishBean {

	private int id;
	private String name;
	private String description;
	private String pic;
	private int price;
	private int restaurant;
	private int window;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	public int getWindow() {
		return window;
	}
	public void setWindow(int window) {
		this.window = window;
	}
}
