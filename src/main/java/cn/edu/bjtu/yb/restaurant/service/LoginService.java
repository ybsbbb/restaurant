package cn.edu.bjtu.yb.restaurant.service;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.StudentBean;

/**
 * 登录注册的所有接口
 * @author yb775
 *
 */
public interface LoginService {

	/**
	 * 获取餐厅信息
	 * @param username
	 * @param password
	 * @return 餐厅对象
	 */
	public RestaurantBean getResInfo(String username, String password);
	
	/**
	 * 获取学生信息
	 * @param username
	 * @param password
	 * @return 学生对象
	 */
	public String getStuInfo(String username, String password);
	
	/**
	 * 添加学生信息
	 * @param stu 学生对象
	 * @return 学生对象
	 */
	public String addStuInfo(StudentBean stu);
	
	/**
	 * 修改学生信息
	 * @param stu 学生对象
	 * @return 学生对象
	 */
	public String setStuInfo(StudentBean stu);
}
