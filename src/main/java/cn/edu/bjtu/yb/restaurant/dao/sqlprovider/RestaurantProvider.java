package cn.edu.bjtu.yb.restaurant.dao.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;

public class RestaurantProvider {

	public String insertProvider(final DishBean dish) {
		String sql = new SQL(){
			{
				INSERT_INTO("dish");
				VALUES("name","#{name}");
				VALUES("description","#{description}");
				VALUES("pic","#{pic}");
				VALUES("price","#{price}");
				VALUES("belongto","#{belongto}");
			}
		}.toString();
		return sql;
	}
}
