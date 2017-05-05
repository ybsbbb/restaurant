package cn.edu.bjtu.yb.restaurant.service;

import java.util.List;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;

public interface OrderService {
	public int addDishOrder(DishOrder dor);
	public int addDishMenu(List<DishMenu> dm);
}
