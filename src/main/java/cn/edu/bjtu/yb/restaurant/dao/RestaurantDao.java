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
	@Select("SELECT * FROM restaurant WHERE username=#{username} and password=#{password}")
	public RestaurantBean queryOne(@Param("username")String username,@Param("password")String password);

	@Select("SELECT id,name,pic FROM restaurant")
	public List<RestaurantBean> queryAll();

	@Select("SELECT * FROM window WHERE restaurant=#{restaurant}")
	public List<WindowBean> queryWindowByRestaurantId(@Param("restaurant")int restaurant);

	@Select("SELECT * FROM dish WHERE restaurant=#{restaurant} and window=#{window}")
	public List<DishBean> queryDishByRestaurantIdAndWindowId(@Param("restaurant")int restaurant,@Param("window")int window);
	
	@InsertProvider(method = "insertProvider", type=RestaurantProvider.class)
	public int insertOne(DishBean dish);
	//数据库详细设计文档化
	//服务器端详细设计文档化，架构，时序图等
	//接口文档
	public static void main(String[] args) throws IOException{
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		dao.queryDishByRestaurantIdAndWindowId(1, 1);
	}
}