package cn.edu.bjtu.yb.restaurant.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bjtu.yb.restaurant.service.LoginService;

@RestController
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	private LoginService loginservice;
	
	@RequestMapping(method=RequestMethod.POST)
	public String getUser(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpSession httpsession) throws IOException {

		String result = loginservice.getStuInfo(username, password);
		if(result != null){
			httpsession.setAttribute("username", username);
		}
		return result;		
	}

}
