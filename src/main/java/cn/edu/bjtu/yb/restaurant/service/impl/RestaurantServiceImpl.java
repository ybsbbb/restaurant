package cn.edu.bjtu.yb.restaurant.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;
import cn.edu.bjtu.yb.restaurant.dao.RestaurantDao;
import cn.edu.bjtu.yb.restaurant.service.RestaurantService;
import cn.edu.bjtu.yb.restaurant.service.StorageService;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

/**
 * RestaurantService的具体实现
 * @author 杨博
 *
 */
@Component
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	StorageService storageService;
	
	@Override
	public String getRestaurantList() {
		JSONArray ja = new JSONArray();
		SqlSession session = null;
		RestaurantDao dao = null;
		List<RestaurantBean> list = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			list = dao.queryAll();
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(session);
		}
		Iterator<RestaurantBean> it = list.iterator();
		while(it.hasNext()){
			JSONObject jo = new JSONObject();
			RestaurantBean bean = it.next();
			jo.put("id", bean.getId());
			jo.put("name", bean.getName());
			jo.put("pic", bean.getPic());
			ja.put(jo);
		}
		System.out.println(ja.toString());
		return ja.toString();
	}

	@Override
	public String getDishListJSON(String restaurant, String window) {
		JSONArray ja = new JSONArray();
		
		int restaurantId = Integer.parseInt(restaurant);
		int windowId = Integer.parseInt(window);
		SqlSession session = null;
		RestaurantDao dao = null;
		List<DishBean> beanList = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			beanList = dao.queryDishByRestaurantIdAndWindowId(restaurantId,windowId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(session);			
		}
		Iterator<DishBean> it = beanList.iterator();
		while(it.hasNext()){
			DishBean bean = it.next();
			JSONObject jo = new JSONObject();
			jo.put("id", bean.getId());
			jo.put("name", bean.getName());
			jo.put("description", bean.getDescription());
			jo.put("pic", bean.getPic());
			jo.put("window", bean.getWindow());
			jo.put("restaurant", bean.getRestaurant());
			jo.put("price", bean.getPrice());
			ja.put(jo.toString());
		}
		System.out.println(ja.toString());
		return ja.toString();
	}

	@Override
	public List<DishBean> getDishListObject(String restaurant, String window) {
		int restaurantId = Integer.parseInt(restaurant);
		int windowId = Integer.parseInt(window);
		SqlSession session = null;
		RestaurantDao dao = null;
		List<DishBean> beanList = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			beanList = dao.queryDishByRestaurantIdAndWindowId(restaurantId,windowId);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(session);			
		}
		return beanList;
	}
	
	@Override
	public int addDish(DishBean dish, MultipartFile file) {
		String filePath = "D:/RESOURCES/STATIC/IMG/DISH/";
		String resourcePath = "/img/dish/";
		String originalName = file.getOriginalFilename();
		String fileName = dish.getRestaurant() //菜样图命名：餐厅+窗口+菜名+后缀
				+ dish.getWindow() 
				+ dish.getName() 
				+ originalName.substring(originalName.lastIndexOf('.'));
		File dest = new File(filePath + fileName);
		storageService.store(dest, file);
		
		dish.setPic(resourcePath + fileName);
		SqlSession session = null;
		RestaurantDao dao = null;
		int result = 0;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			result = dao.insertOne(dish);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			session.close();			
		}
		return result;
	}

	@Override
	public int editDish(DishBean dish, MultipartFile file) {
		SqlSession session = null;
		RestaurantDao dao = null;
		DishBean oridb = null;
		try{
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			oridb = dao.queryDishBean(dish.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		String filePath = "D:/RESOURCES/STATIC/IMG/DISH/";
		String resourcePath = "/img/dish/";
		String originalName = file.getOriginalFilename();
		String fileName = oridb.getRestaurant() //菜样图命名：餐厅+窗口+菜名+后缀
				+ oridb.getWindow() 
				+ dish.getName() 
				+ originalName.substring(originalName.lastIndexOf('.'));
		File dest = new File(filePath + fileName);
		storageService.store(dest, file);
		
		dish.setPic(resourcePath + fileName);
		int result = 0;
		result = dao.insertOne(dish);
		session.commit();
		session.close();
		return result;
	}

	@Override
	public String getWindowListJSON(String restaurant) {
		JSONArray ja = new JSONArray();
		SqlSession session = null;
		RestaurantDao dao = null;
		int restaurantId = Integer.parseInt(restaurant);
		List<WindowBean> beanlist = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			beanlist = dao.queryWindowByRestaurantId(restaurantId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(session);			
		}
		Iterator<WindowBean> it = beanlist.iterator();
		while(it.hasNext()) {
			WindowBean wb = it.next();
			JSONObject jo = new JSONObject();
			
			jo.put("id", wb.getId());
			jo.put("name", wb.getName());
			
			ja.put(jo.toString());
		}
		return ja.toString();
	}

	@Override
	public List<WindowBean> getWindowListObject(String restaurant) {
		SqlSession session = null;
		RestaurantDao dao = null;
		int restaurantId = Integer.parseInt(restaurant);
		List<WindowBean> beanlist = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			beanlist = dao.queryWindowByRestaurantId(restaurantId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.closeSession(session);
		}
		return beanlist;
	}
	
	
	public static void main(String[] args) throws IOException{
		RestaurantServiceImpl rsi = new RestaurantServiceImpl();
		rsi.getDishListJSON("1","1");
	}

	@Override
	public DishBean getDishBean(String dishid) {
		SqlSession session = null;
		RestaurantDao dao = null;
		int dishId = Integer.parseInt(dishid);
		DishBean db = null;
		try {
			session = SqlUtil.getSession();
			dao = session.getMapper(RestaurantDao.class);
			db = dao.queryDishBean(dishId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.closeSession(session);
		}
		return db;
	}

}
