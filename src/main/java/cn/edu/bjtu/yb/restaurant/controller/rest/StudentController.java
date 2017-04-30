package cn.edu.bjtu.yb.restaurant.controller.rest;

import java.io.IOException;

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
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getUser(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletResponse response,
			HttpSession httpsession) throws IOException {

		String result = loginService.getStuInfo(username, password);
		if(result != null){
			httpsession.setAttribute("token", username);
			Cookie cookie = new Cookie("token",username);
			response.addCookie(cookie);
		}
		return result;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addUser(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "age", required=false) int age,
			@RequestParam(value = "gender", required=false) String gender,
			HttpServletResponse response,
			HttpSession httpsession) throws IOException{
		StudentBean stu = new StudentBean();
		stu.setUsername(username);
		stu.setPassword(password);
		stu.setName(name);
		stu.setAge(age);
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
