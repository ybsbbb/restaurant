package cn.edu.bjtu.yb.restaurant.controller.web;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.service.LoginService;

/**
 * 
 * @author 杨博
 * <p>该类为主页面的controller
 * <p>用户访问时，判断token是否存在，是否合法
 * <p>如是，进入用户主页面
 * <p>否则，进入登录页面
 * 
 */
@Controller
public class HomeController {
	
	@Autowired
	LoginService loginservice;

	/**
	 * <p>用户直接输入"/home"路由的controller,
	 * <p>若已经登录,则跳转至用户主界面,
	 * <p>否则，跳转至登录界面
	 * @param token 用户客户端cookie,用于标识身份
	 * @param response
	 * @param httpsession
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/home"}, method=RequestMethod.GET)
	public String home(
			@CookieValue(value="token") String token,
			HttpServletResponse response,
			HttpSession httpsession,
			Model model){
		if(token != null && httpsession.getAttribute("token") != null){
			if(((String)httpsession.getAttribute("token")).equals(token)) {
				RestaurantBean res = (RestaurantBean) httpsession.getAttribute("res");
				model.addAttribute("restaurant", res);
				return "_home";
			}
		}
		return "_login";
	}
	
	/**
	 * <p>用户在登录界面点击登录按钮的路由,
	 * <p>验证用户名密码后,根据验证结果,
	 * <p>如果正确,跳转至用户主界面
	 * <p>否则,跳转至登录界面
	 * @param username
	 * @param password
	 * @param response
	 * @param httpsession
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value={"/home"}, method=RequestMethod.POST)
	public String home(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			HttpServletResponse response,
			HttpSession httpsession,
			Model model) throws IOException{
		
		RestaurantBean res = loginservice.getResInfo(username, password);
		
		if(res != null){
			System.out.println(res.getName());
			httpsession.setAttribute("token", username);
			httpsession.setAttribute("res", res);
			model.addAttribute("restaurant", res);
			Cookie _token = new Cookie("token", res.getUsername());
			response.addCookie(_token);
			return "_home";
		}
		return "_login";
	}
}
