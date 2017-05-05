package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;
import cn.edu.bjtu.yb.restaurant.dao.sqlprovider.RestaurantProvider;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface RestaurantDao {
	/**
	 * 查询餐厅信息，用于餐厅用户登录
	 * @param username
	 * @param password
	 * @return 餐厅对象
	 */
	@Select("SELECT * FROM restaurant WHERE username=#{username} and password=#{password}")
	public RestaurantBean queryOne(@Param("username")String username,@Param("password")String password);

	/**
	 * 查询所有餐厅
	 * @return 餐厅对象的LIST
	 */
	@Select("SELECT id,name,pic FROM restaurant")
	public List<RestaurantBean> queryAll();

	/**
	 * 根据餐厅Id查询窗口
	 * @param restaurant
	 * @return 窗口LIST
	 */
	@Select("SELECT * FROM window WHERE restaurant=#{restaurant}")
	public List<WindowBean> queryWindowByRestaurantId(@Param("restaurant")int restaurant);

	/**
	 * 根据餐厅和窗口id查询菜
	 * @param restaurant
	 * @param window
	 * @return 菜LIST
	 */
	@Select("SELECT * FROM dish WHERE restaurant=#{restaurant} and window=#{window}")
	public List<DishBean> queryDishByRestaurantIdAndWindowId(@Param("restaurant")int restaurant,@Param("window")int window);
	
	/**
	 * 向数据库添加菜
	 * @param dish
	 * @return 添加结果 1表示添加成功
	 */
	@InsertProvider(method = "insertProvider", type=RestaurantProvider.class)
	public int insertOne(DishBean dish);
	public static void main(String[] args) throws IOException{
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		dao.queryDishByRestaurantIdAndWindowId(1, 1);
	}
}