package cn.edu.bjtu.yb.restaurant.service;

import java.io.IOException;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.StudentBean;

public interface LoginService {

	public RestaurantBean getResInfo(String username, String password) throws IOException;
	public String getStuInfo(String username, String password) throws IOException;
	public String addStuInfo(StudentBean stu);
	public String setStuInfo(StudentBean stu);
}
