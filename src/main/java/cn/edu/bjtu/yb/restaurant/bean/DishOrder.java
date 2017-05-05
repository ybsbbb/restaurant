package cn.edu.bjtu.yb.restaurant.bean;

import java.sql.Timestamp;

/**
 * <p>订单模型
 * <p>包含一个订单的基本属性
 * <p>订单id,客户id,所属窗口id,价格,下单时间,取餐时间,订单状态(0 刚刚下单,1 餐厅已开始准备食材,2 餐厅已做好，等待取餐,3 订单已完成,4 订单被取消)
 * @author yb775
 *
 */
public class DishOrder {

	private String id;
	private String customer;
	private int restaurant;
	private int price;
	private Timestamp ordertime;
	private Timestamp taketime;
	private int state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public Timestamp getTaketime() {
		return taketime;
	}
	public void setTaketime(Timestamp taketime) {
		this.taketime = taketime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
