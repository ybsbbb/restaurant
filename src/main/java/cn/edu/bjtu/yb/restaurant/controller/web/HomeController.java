package cn.edu.bjtu.yb.restaurant.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired
	LoginService loginservice;

	@RequestMapping(value={"/home"}, method=RequestMethod.POST)
	public String home(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			HttpSession httpsession,
			Model model) throws IOException{
		
		RestaurantBean res = loginservice.getResInfo(username, password);
		
		if(res != null){
			System.out.println(res.getResname());
			httpsession.setAttribute("username", username);
			httpsession.setAttribute("resname", res.getResname());
			return "home";
		}
		return "login";
	}
}
