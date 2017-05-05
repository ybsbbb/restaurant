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

/**
 * LoginService的具体实现
 * @author 杨博
 *
 */
@Component
public class LoginServiceImpl implements LoginService {

	@Override
	public RestaurantBean getResInfo(String username, String password) {

		SqlSession sqlsession = null;
		RestaurantDao resdao = null;
		RestaurantBean res = null;
		try {
			sqlsession = SqlUtil.getSession();
			resdao = sqlsession.getMapper(RestaurantDao.class);
			res = resdao.queryOne(username, password);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(sqlsession);
		}
		return res;
	}

	@Override
	public String getStuInfo(String username, String password) {

		SqlSession sqlsession = null;
		StudentDao studao = null;
		StudentBean stu = null;
		try {
			sqlsession = SqlUtil.getSession();
			studao = sqlsession.getMapper(StudentDao.class);
			stu = studao.queryOne(username, password);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(sqlsession);			
		}
		
		if(stu != null) {
			JSONObject jo = new JSONObject();
			jo.put("username", stu.getUsername());
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
		
		SqlSession sqlsession = null;
		StudentDao studao = null;
		int result = 0;
		try {
			sqlsession = SqlUtil.getSession();
			studao = sqlsession.getMapper(StudentDao.class);
			if(stu.getName() == null) {
				stu.setName("匿名");
			}
			result = studao.insertOne(stu);
			sqlsession.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(sqlsession);			
		}
		if(result == 1){
			JSONObject jo = new JSONObject();
			jo.put("username", stu.getUsername());
			jo.put("name", stu.getName());
			jo.put("age",stu.getAge());
			jo.put("gender", stu.getGender());
			jo.put("account", stu.getAccount());
			System.out.println(jo.toString());
			return jo.toString();
		}		
		return null;
	}

	@Override
	public String setStuInfo(StudentBean stu) {
		SqlSession sqlsession = null;
		StudentDao studao = null;
		int result = 0;
		try {
			sqlsession = SqlUtil.getSession();
			studao = sqlsession.getMapper(StudentDao.class);
			result = studao.updateOne(stu);
			sqlsession.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			SqlUtil.closeSession(sqlsession);
		}
		if(result == 1){
			JSONObject jo = new JSONObject();
			jo.put("username", stu.getUsername());
			jo.put("name", stu.getName());
			jo.put("age",stu.getAge());
			jo.put("gender", stu.getGender());
			jo.put("account", stu.getAccount());
			System.out.println(jo.toString());
			return jo.toString();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		LoginServiceImpl lsi = new LoginServiceImpl();
		lsi.getStuInfo("test", "test");
	}
}
