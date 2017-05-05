package cn.edu.bjtu.yb.restaurant.bean;

/**
 * <p>订单中的菜单模型
 * <p>包括所属订单的id，所属窗口，菜id，份数
 * @author 杨博
 *
 */
public class DishMenu {

	private String orderid;
	private int window;
	private int dish;
	private int number;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getWindow() {
		return window;
	}
	public void setWindow(int window) {
		this.window = window;
	}
	public int getDish() {
		return dish;
	}
	public void setDish(int dish) {
		this.dish = dish;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
