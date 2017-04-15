package cn.edu.bjtu.yb.restaurant.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"/login","/"})
	public String login() {
		return "login";
	}
	
	@RequestMapping("/rest")
	public String users() {
		return "resttest";
	}
	
}
