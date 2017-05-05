package cn.edu.bjtu.yb.restaurant.service;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.StudentBean;

public interface LoginService {

	public RestaurantBean getResInfo(String username, String password);
	public String getStuInfo(String username, String password);
	public String addStuInfo(StudentBean stu);
	public String setStuInfo(StudentBean stu);
}
