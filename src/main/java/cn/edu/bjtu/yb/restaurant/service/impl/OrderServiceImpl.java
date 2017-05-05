package cn.edu.bjtu.yb.restaurant.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.dao.OrderDao;
import cn.edu.bjtu.yb.restaurant.service.OrderService;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

@Component
public class OrderServiceImpl implements OrderService {

	@Override
	public int addDishOrder(DishOrder dor) {
		SqlSession session = null;
		OrderDao dd = null;
		int result = 0;
		try {
			session = SqlUtil.getSession();
			dd = session.getMapper(OrderDao.class);
			result = dd.insertOneOrder(dor);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();			
		}
		return result;
	}

	@Override
	public int addDishMenu(List<DishMenu> dm) {
		SqlSession session = null;
		OrderDao dd = null;
		int result = 0;
		try {
			session = SqlUtil.getSession();
			dd = session.getMapper(OrderDao.class);
			result = dd.insertMenus(dm);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return result;
	}

	
}
