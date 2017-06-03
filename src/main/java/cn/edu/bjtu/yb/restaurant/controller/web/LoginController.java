package cn.edu.bjtu.yb.restaurant.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * 负责返回登录页面
	 * @param token
	 * @return 根据token验证结果，返回登录页面或HOME页面
	 */
	@RequestMapping({"/login"})
	public String login(@CookieValue(value = "token", required = false) String token) {
		if(token != null) {
			return "forward:home";
		}
		return "tlogin";
	}

}
