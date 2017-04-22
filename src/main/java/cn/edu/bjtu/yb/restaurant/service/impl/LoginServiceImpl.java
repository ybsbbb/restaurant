package cn.edu.bjtu.yb.restaurant.service.impl;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.StudentBean;
import cn.edu.bjtu.yb.restaurant.dao.RestaurantDao;
import cn.edu.bjtu.yb.restaurant.dao.StudentDao;
import cn.edu.bjtu.yb.restaurant.service.LoginService;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

@Component
public class LoginServiceImpl implements LoginService {

	@Override
	public RestaurantBean getResInfo(String username, String password) throws IOException {

		SqlSession sqlsession = SqlUtil.getSession();
		RestaurantDao resdao = sqlsession.getMapper(RestaurantDao.class);
		RestaurantBean res = resdao.queryOne(username, password);
		SqlUtil.closeSession(sqlsession);
		return res;
	}

	@Override
	public String getStuInfo(String username, String password) throws IOException {

		SqlSession sqlsession = SqlUtil.getSession();
		StudentDao studao = sqlsession.getMapper(StudentDao.class);
		StudentBean stu = studao.queryOne(username, password);
		SqlUtil.closeSession(sqlsession);
		
		if(stu != null) {
			JSONObject jo = new JSONObject();
			jo.put("token", stu.getUsername());
			jo.put("name", stu.getName());
			jo.put("age", stu.getAge());
			jo.put("gender", "0".equals(stu.getGender()) ? "男" : "1".equals(stu.getGender()) ? "女": "无");
			jo.put("account", stu.getAccount() / 100.0);
			System.out.println(jo.toString());
			return jo.toString();
		}
		System.out.println("null");
		return null;
	}

	@Override
	public String addStuInfo(StudentBean stu) {
		return null;
	}

	@Override
	public String setStuInfo(StudentBean stu) {
		return null;
	}

	public static void main(String[] args) throws IOException {
		LoginServiceImpl lsi = new LoginServiceImpl();
		lsi.getStuInfo("test", "test");
	}
}
