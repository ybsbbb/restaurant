package cn.edu.bjtu.yb.restaurant.bean;

/**
 * <p>餐厅模型
 * @author 杨博
 *
 */
public class RestaurantBean {

	private int id;
	private String username;
	private String password;
	private String name;
	private String pic;//餐厅图片uri，用于android客户端显示
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
