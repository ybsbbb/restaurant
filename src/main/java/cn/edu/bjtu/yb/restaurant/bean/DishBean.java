package cn.edu.bjtu.yb.restaurant.bean;

/**
 * 菜的模型，包含一个菜的所有属性
 * @author 杨博
 *
 */
public class DishBean {

	private int id;
	private String name;
	private String description;
	private String pic;//图片uri
	private int price;
	private int restaurant;//所属餐厅
	private int window;//所属窗口
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
