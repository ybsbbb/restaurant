package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface OrderDao {

	@Insert("insert into dishorder(id,customer,restaurant,price,taketime,state)"
			+ "values(#{dor.id}, #{dor.customer}, #{dor.restaurant}, #{dor.price}, #{dor.taketime}, #{dor.state})")
	public int insertOneOrder(@Param("dor") DishOrder dishorder);
	
	public int insertMenus(List<DishMenu> menu);

	public static void main(String[] args) throws IOException {
		SqlSession s = SqlUtil.getSession();
		@SuppressWarnings("unused")
		OrderDao o = s.getMapper(OrderDao.class);
		s.commit();
		s.close();
	}
}