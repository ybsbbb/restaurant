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
	public String getDishList(String restaurant) throws IOException {
		JSONArray ja = new JSONArray();
		
		int belongto = Integer.parseInt(restaurant);
		SqlSession session = SqlUtil.getSession();
		RestaurantDao dao = session.getMapper(RestaurantDao.class);
		List<DishBean> beanList = dao.queryDishByRestaurantId(belongto);
		Iterator<DishBean> it = beanList.iterator();
		while(it.hasNext()){
			DishBean bean = it.next();
			JSONObject jo = new JSONObject();
			jo.put("id", bean.getId());
			jo.put("name", bean.getName());
			jo.put("description", bean.getDescription());
			jo.put("pic", bean.getPic());
			jo.put("belongto", bean.getBelongto());
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
		String fileName = dish.getName() + originalName.substring(originalName.lastIndexOf('.'));
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

	public static void main(String[] args) throws IOException{
		RestaurantServiceImpl impl = new RestaurantServiceImpl();
		impl.getDishList("2");
	}
}
