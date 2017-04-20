package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.dao.sqlprovider.RestaurantProvider;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface RestaurantDao {
	@Select("SELECT * FROM restaurant WHERE username=#{username} and password=#{password}")
	public RestaurantBean queryOne(@Param("username")String username,@Param("password")String password);

	@Select("SELECT id,name,pic FROM restaurant")
	public List<RestaurantBean> queryAll();
	
	@Select("SELECT * FROM dish WHERE belongto=#{belongto}")
	public List<DishBean> queryDishByRestaurantId(int belongto);
	
	@Select("SELECT * FROM dishdata")
	public int queryNextDishId();
	
	@InsertProvider(method = "insertProvider", type=RestaurantProvider.class)
	public int insertOne(DishBean dish);
	
	public static void main(String[] args) throws IOException{
		DishBean dish = new DishBean();
		dish.setBelongto(1);
		dish.setDescription("测试菜8，请勿食用");
		dish.setName("测试菜8");
		dish.setPic("/img/dish/8.jpg");
		dish.setPrice(1000);
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		dao.insertOne(dish);
		session.commit();
		session.close();
	}
}