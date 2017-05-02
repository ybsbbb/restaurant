package cn.edu.bjtu.yb.restaurant.controller.web;

import java.io.IOException;
import java.util.List;

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

import cn.edu.bjtu.yb.restaurant.bean.DishBean;
import cn.edu.bjtu.yb.restaurant.bean.RestaurantBean;
import cn.edu.bjtu.yb.restaurant.bean.WindowBean;
import cn.edu.bjtu.yb.restaurant.service.LoginService;
import cn.edu.bjtu.yb.restaurant.service.RestaurantService;

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
	
	@Autowired
	RestaurantService restaurantservice;

	/**
	 * <p>用户直接输入"/home"路由的controller,
	 * <p>若已经登录,则跳转至用户主界面,
	 * <p>否则，跳转至登录界面
	 * @param token 用户客户端cookie,用于标识身份
	 * @param response
	 * @param httpsession
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value={"/home"}, method=RequestMethod.GET)
	public String home(
			@CookieValue(value="token", required=false) String token,
			HttpServletResponse response,
			HttpSession httpsession,
			Model model) throws IOException{
		if(token != null && httpsession.getAttribute("token") != null){
			if(((String)httpsession.getAttribute("token")).equals(token)) {
				RestaurantBean res = (RestaurantBean) httpsession.getAttribute("res");
				model.addAttribute("restaurant", res);
				List<WindowBean> windows = restaurantservice.getWindowListObject(res.getId() + "");
				for(WindowBean wb : windows){
					List<DishBean> db = restaurantservice.getDishListObject(res.getId() + "", wb.getId() + "");
					wb.setDishes(db);
				}
				model.addAttribute("windows", windows);
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
			httpsession.setAttribute("token", res.getId() + "");
			httpsession.setAttribute("res", res);
			model.addAttribute("restaurant", res);
			Cookie _token = new Cookie("token", res.getId() + "");
			response.addCookie(_token);
			List<WindowBean> windows = restaurantservice.getWindowListObject(res.getId() + "");
			for(WindowBean wb : windows){
				List<DishBean> db = restaurantservice.getDishListObject(res.getId() + "", wb.getId() + "");
				wb.setDishes(db);
			}
			model.addAttribute("windows", windows);
			return "_home";
		}
		return "_login";
	}
}
