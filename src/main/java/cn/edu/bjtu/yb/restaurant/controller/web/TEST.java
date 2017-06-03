package cn.edu.bjtu.yb.restaurant.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TEST {

	@RequestMapping("/timer")
	public String timer() {
		return "_timer";
	}
	
	@RequestMapping("/stu")
	public String student(){
		return "_stu";
	}

	@RequestMapping("/addorder")
	public String order(){
		return "_order";
	}
	
	@RequestMapping("/restapi")
	public String users(Model model) {
		List<APIShow> webapi = new ArrayList<APIShow>();
		APIShow a3 = new APIShow();
		a3.setMethod("GET");
		a3.setRealLink("http://localhost:8080/");
		a3.setShowLink("http://localhost:8080/");
		webapi.add(a3);
		APIShow a1 = new APIShow();
		a1.setMethod("GET");
		a1.setRealLink("http://localhost:8080/login");
		a1.setShowLink("http://localhost:8080/login");
		webapi.add(a1);
		APIShow a2 = new APIShow();
		a2.setMethod("GET");
		a2.setRealLink("http://localhost:8080/home");
		a2.setShowLink("http://localhost:8080/home");
		webapi.add(a2);
		APIShow a4 = new APIShow();
		a4.setMethod("GET");
		a4.setRealLink("http://localhost:8080/adddish");
		a4.setShowLink("http://localhost:8080/adddish");
		webapi.add(a4);
		
		
		List<APIShow> restapi = new ArrayList<APIShow>();
		APIShow b1 = new APIShow();
		b1.setMethod("GET");
		b1.setRealLink("http://localhost:8080/restaurants");
		b1.setShowLink("http://localhost:8080/restaurants");
		restapi.add(b1);
		
		APIShow b2 = new APIShow();
		b2.setMethod("GET");
		b2.setRealLink("http://localhost:8080/restaurants/1/windows");
		b2.setShowLink("http://localhost:8080/restaurants/1/windows");
		restapi.add(b2);

		APIShow b3 = new APIShow();
		b3.setMethod("GET");
		b3.setRealLink("http://localhost:8080/restaurants/1/windows/1");
		b3.setShowLink("http://localhost:8080/restaurants/1/windows/1");		
		restapi.add(b3);

		APIShow b4 = new APIShow();
		b4.setMethod("POST");
		b4.setRealLink("http://localhost:8080/adddish");
		b4.setShowLink("http://localhost:8080/restaurants/1/windows/1");		
		restapi.add(b4);

		APIShow b5 = new APIShow();
		b5.setMethod("GET");
		b5.setRealLink("http://localhost:8080/stu");
		b5.setShowLink("http://localhost:8080/user");		
		restapi.add(b5);

		APIShow b6 = new APIShow();
		b6.setMethod("POST");
		b6.setRealLink("http://localhost:8080/stu");
		b6.setShowLink("http://localhost:8080/user");		
		restapi.add(b6);

		APIShow b7 = new APIShow();
		b7.setMethod("POST");
		b7.setRealLink("http://localhost:8080/addorder");
		b7.setShowLink("http://localhost:8080/orders/order");		
		restapi.add(b7);
		
		model.addAttribute("webapis",webapi);
		model.addAttribute("restapis", restapi);
		return "_restapi";
	}
}

class APIShow{
	private String realLink;
	private String showLink;
	private String method;
	public String getRealLink() {
		return realLink;
	}
	public void setRealLink(String realLink) {
		this.realLink = realLink;
	}
	public String getShowLink() {
		return showLink;
	}
	public void setShowLink(String showLink) {
		this.showLink = showLink;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
