package cn.edu.bjtu.yb.restaurant.service;

import java.io.IOException;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;

public interface LoginService {

	public RestaurantBean getResInfo(String username, String password) throws IOException;
	public String getStuInfo(String username, String password) throws IOException;
}
