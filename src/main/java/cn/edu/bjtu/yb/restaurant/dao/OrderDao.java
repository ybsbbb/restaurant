package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface OrderDao {

	/**
	 * 向数据库插入订单
	 * @param dishorder
	 * @return
	 */
	@Insert("insert into dishorder(id,customer,restaurant,price,taketime,state)"
			+ "values(#{dor.id}, #{dor.customer}, #{dor.restaurant}, #{dor.price}, #{dor.taketime}, #{dor.state})")
	public int insertOneOrder(@Param("dor") DishOrder dishorder);
	
	/**
	 * 向数据库插入菜单
	 * @param menu
	 * @return
	 */
	public int insertMenus(List<DishMenu> menu);
	
	@Select("select id, price, ordertime, state from dishorder where customer = #{userid} order by state asc,ordertime desc")
	public List<DishOrder> getOrdersByUser(@Param("userid") String userid);

	@Select("select id, price, ordertime from dishorder where restaurant = #{restaurantid}")
	public List<DishOrder> getOrdersByRestaurant(@Param("restaurantid") String restaurantid);

	@Select("select id, price, ordertime from dishorder where restaurant = #{restaurantid} and state = #{state}")
	public List<DishOrder> getOrdersByRestaurantAndState(@Param("restaurantid") String restaurantid,@Param("state") int state);

	@Select("select * from dishorder where id = #{id}")
	public DishOrder getOrderById(@Param("id") String id);

	@Select("select * from dishmenu where orderid = #{orderid}")
	public List<DishMenu> getMenusByOrder(@Param("orderid") String orderid);

	@Update("update dishorder set state = #{state} where id = #{id}")
	public int updateOrderState(@Param("state") String state,@Param("id") String id);
	
	public static void main(String[] args) throws IOException {
		SqlSession s = SqlUtil.getSession();
		@SuppressWarnings("unused")
		OrderDao o = s.getMapper(OrderDao.class);
		
		s.commit();
		s.close();
	}
}