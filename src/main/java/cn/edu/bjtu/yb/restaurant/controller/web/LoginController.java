package cn.edu.bjtu.yb.restaurant.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping({"/login"})
	public String login(@CookieValue(value = "token", required = false) String token) {
		if(token != null) {
			return "forward:home";
		}
		return "_login";
	}

}
