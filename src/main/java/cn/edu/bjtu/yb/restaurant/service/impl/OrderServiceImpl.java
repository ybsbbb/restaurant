package cn.edu.bjtu.yb.restaurant.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;
import cn.edu.bjtu.yb.restaurant.dao.OrderDao;
import cn.edu.bjtu.yb.restaurant.dao.RestaurantDao;
import cn.edu.bjtu.yb.restaurant.service.OrderService;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

/**
 * OrderService的具体实现
 * @author 杨博
 *
 */
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

	@Override
	public String getOrdersByUser(String uid) {
		SqlSession session = null;
		List<DishOrder> dors = null;
		OrderDao od = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			dors = od.getOrdersByUser(uid);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		JSONArray ja = new JSONArray();
		for(DishOrder dor : dors) {
			JSONObject jo = new JSONObject();
			jo.put("id", dor.getId());
			jo.put("price", dor.getPrice());
			jo.put("ordertime", dor.getOrdertime());
			jo.put("state", dor.getState());
			ja.put(jo);
		}
		System.out.println(ja);
		return ja.toString();
	}

	@Override
	public String getOrdersByRestaurant(String rid) {
		SqlSession session = null;
		List<DishOrder> dors = null;
		OrderDao od = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			dors = od.getOrdersByRestaurant(rid);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		JSONArray ja = new JSONArray();
		for(DishOrder dor : dors) {
			JSONObject jo = new JSONObject();
			jo.put("id", dor.getId());
			jo.put("price", dor.getPrice());
			jo.put("ordertime", dor.getOrdertime());
			ja.put(jo);
		}
		System.out.println(ja);
		return ja.toString();
	}

	@Override
	public List<DishOrder> getOrdersByRestaurantAndState(String rid, int state) {
		SqlSession session = null;
		List<DishOrder> dors = null;
		OrderDao od = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			dors = od.getOrdersByRestaurantAndState(rid,state);
			if(state == 1){
				List<DishOrder> tdors = od.getOrdersByRestaurantAndState(rid,2);
				for(DishOrder d : tdors){
					dors.add(d);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return dors;
	}

	@Override
	public String getOrderById(String id) {
		SqlSession session = null;
		DishOrder dor = null;
		OrderDao od = null;
		RestaurantDao rd = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			rd = session.getMapper(RestaurantDao.class);
			dor = od.getOrderById(id);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		JSONObject jo = new JSONObject();
		jo.put("id", dor.getId());
		jo.put("price", dor.getPrice());
		jo.put("ordertime", dor.getOrdertime());
		RestaurantBean rb = rd.queryRestaurantById(dor.getRestaurant());
		jo.put("restaurant", rb.getName());
		jo.put("state", dor.getState());
		jo.put("customer", dor.getCustomer());
		jo.put("taketime", dor.getTaketime());
		jo.put("menu", getMenusByOrderId(dor.getId()).toString());
		System.out.println(jo);
		session.close();
		return jo.toString();
	}

	@Override
	public String getMenusByOrderId(String oid) {
		SqlSession session = null;
		List<DishMenu> menus = null;
		OrderDao od = null;
		RestaurantDao rd = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			rd = session.getMapper(RestaurantDao.class);
			menus = od.getMenusByOrder(oid);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		JSONArray ja = new JSONArray();
		for(DishMenu menu : menus) {
			JSONObject jo = new JSONObject();
			DishBean tmpd = rd.queryDishBean(menu.getDish());
			WindowBean tmpw = rd.queryWindowById(menu.getWindow());
			jo.put("dish", tmpd.getName());
			jo.put("number", menu.getNumber());
			jo.put("window", tmpw.getName());
			ja.put(jo);
		}
		session.close();
		System.out.println(ja);
		return ja.toString();
	}
	
	@Override
	public String changeOrderState(String oid, String state) {
		SqlSession session = null;
		OrderDao od = null;
		DishOrder dor = null;
		try {
			session = SqlUtil.getSession();
			od = session.getMapper(OrderDao.class);
			dor = od.getOrderById(oid);
			switch(state) {
			case "1"://准备食材
				if(dor.getState() == 0) {
					od.updateOrderState(state, oid);
					return state;
				}
				break;
			case "2"://已做好
				if(dor.getState() == 1) {
					od.updateOrderState(state, oid);
					return state;
				}
				break;
			case "3"://订单完成
				if(dor.getState() == 2) {
					od.updateOrderState(state, oid);
					return state;
				}
				break;
			case "4"://订单取消
				if(dor.getState() == 0) {
					od.updateOrderState(state, oid);
					return state;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return "fail";
	}
	
	public static void main(String[] args) {
		OrderServiceImpl osi = new OrderServiceImpl();
		System.out.println(osi.getOrderById("201705041713320000"));
	}
	
}
