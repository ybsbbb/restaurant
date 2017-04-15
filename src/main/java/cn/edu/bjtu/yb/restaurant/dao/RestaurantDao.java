package cn.edu.bjtu.yb.restaurant.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;

public interface RestaurantDao {
	@Select("SELECT * FROM resuser WHERE username=#{username} and password=#{password}")
	public RestaurantBean queryOne(@Param("username")String username,@Param("password")String password);

}