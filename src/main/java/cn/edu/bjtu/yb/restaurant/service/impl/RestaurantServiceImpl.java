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

@Component
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	StorageService storageService;
	
	@Override
	public String getRestaurantList() throws IOException {
		JSONArray ja = new JSONArray();
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		List<RestaurantBean> list = dao.queryAll();
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
	public String getDishList(String restaurant, String window) throws IOException {
		JSONArray ja = new JSONArray();
		
		int restaurantId = Integer.parseInt(restaurant);
		int windowId = Integer.parseInt(window);
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		List<DishBean> beanList = dao.queryDishByRestaurantIdAndWindowId(restaurantId,windowId);
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
	public int addDish(DishBean dish, MultipartFile file) throws IOException {
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
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		dao.insertOne(dish);
		session.commit();
		session.close();
		return 0;
	}

	@Override
	public String getWindowList(String restaurant) throws IOException {
		JSONArray ja = new JSONArray();
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		int restaurantId = Integer.parseInt(restaurant);
		List<WindowBean> beanlist = dao.queryWindowByRestaurantId(restaurantId);
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

	public static void main(String[] args) throws IOException{
		RestaurantServiceImpl rsi = new RestaurantServiceImpl();
		rsi.getDishList("1","1");
	}
}
