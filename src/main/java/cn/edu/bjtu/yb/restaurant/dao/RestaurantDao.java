package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface RestaurantDao {
	@Select("SELECT * FROM restaurant WHERE username=#{username} and password=#{password}")
	public RestaurantBean queryOne(@Param("username")String username,@Param("password")String password);

	@Select("SELECT name,pic FROM restaurant")
	public List<RestaurantBean> queryAll();
	
	public static void main(String[] args) throws IOException{
		Iterator<RestaurantBean> it = SqlUtil.getSession().getMapper(RestaurantDao.class).queryAll().iterator();
		while(it.hasNext()){
			RestaurantBean rb = it.next();
			System.out.println(rb.getName() + "  " +rb.getPic());
		}
	}
}