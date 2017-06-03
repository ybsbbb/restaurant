package cn.edu.bjtu.yb.restaurant.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	/**
	 * <p>"/"路由,根据token情况,决定跳转至用户主界面或是登录界面
	 * @param token
	 * @param httpsession
	 * @param model
	 * @return
	 */
	@RequestMapping({"/"})
	public String index(@CookieValue(value="token", required = false) String token,
			HttpSession httpsession,
			Model model) {
		if(token != null) {
			return "forward:home";
		}
		return "tlogin";
	}
	
}
