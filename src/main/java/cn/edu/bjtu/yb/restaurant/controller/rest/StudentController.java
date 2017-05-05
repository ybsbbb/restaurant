package cn.edu.bjtu.yb.restaurant.controller.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bjtu.yb.restaurant.bean.StudentBean;
import cn.edu.bjtu.yb.restaurant.service.LoginService;

@RestController
@RequestMapping("/user")
public class StudentController {
	
	/**
	 * 登录注册的service
	 */
	@Autowired
	private LoginService loginService;
	
	/**
	 * 提交用户名密码，验证身份登录
	 * @param username
	 * @param password
	 * @param response HttpResponse,用于添加COOKIE
	 * @param httpsession HttpSession,用于存储token
	 * @return 用户个人信息的JSON字符串
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String getUser(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletResponse response,
			HttpSession httpsession) {

		String result = loginService.getStuInfo(username, password);
		if(result != null){
			httpsession.setAttribute("token", username);
			Cookie cookie = new Cookie("token",username);
			response.addCookie(cookie);
		}
		return result;
	}
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param name
	 * @param age
	 * @param gender
	 * @param response
	 * @param httpsession
	 * @return 用户个人信息的JSON字符串
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String addUser(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "age", required=false) String age,
			@RequestParam(value = "gender", required=false) String gender,
			HttpServletResponse response,
			HttpSession httpsession) {
		StudentBean stu = new StudentBean();
		stu.setUsername(username);
		stu.setPassword(password);
		stu.setName(name);
		stu.setAge(age == null ? 0:Integer.parseInt(age));
		stu.setGender(gender);
		
		String result = loginService.addStuInfo(stu);
		
		if(result != null){
			httpsession.setAttribute("token", username);
			Cookie cookie = new Cookie("token",username);
			response.addCookie(cookie);
		}
		
		return result;
	}
	
}
