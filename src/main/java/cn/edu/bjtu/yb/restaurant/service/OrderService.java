package cn.edu.bjtu.yb.restaurant.service;

import java.util.List;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;

/**
 * 有关订单的服务层所有接口
 * @author 杨博
 *
 */
public interface OrderService {
	/**
	 * 插入订单信息
	 * @param dor 订单对象
	 * @return 1表示成功
	 */
	public int addDishOrder(DishOrder dor);
	/**
	 * 插入菜单信息
	 * @param dm 菜单LIST
	 * @return 大于或等于1表示成功
	 */
	public int addDishMenu(List<DishMenu> dm);
}
