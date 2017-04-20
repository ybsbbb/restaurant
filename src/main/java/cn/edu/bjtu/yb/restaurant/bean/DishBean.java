package cn.edu.bjtu.yb.restaurant.bean;

public class DishBean {

	private int id;
	private String name;
	private String description;
	private String pic;
	private int price;
	private int belongto;
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
	public int getBelongto() {
		return belongto;
	}
	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}
}
