package cn.edu.bjtu.yb.restaurant.controller.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.service.OrderService;
import cn.edu.bjtu.yb.restaurant.util.OrderGeneration;

@RestController
public class OrderController {

	@Autowired
	private OrderGeneration orderGeneration;

	@Autowired
	private OrderService orderService;	
	
	@PostMapping("/orders/order")
	public String addOrder(
			@RequestParam("order") String order,
			@RequestParam("username") String username
			) {
		JSONObject jo = new JSONObject(order);
		JSONArray ja = (JSONArray)jo.get("menu");
		DishOrder dor = new DishOrder();
		List<DishMenu> dmlist = new ArrayList<DishMenu>();
		
		String orderid = orderGeneration.genOrderNumber();
		
		dor.setId(orderid);
		dor.setCustomer(username);
		dor.setRestaurant(jo.getInt("restaurant"));
		dor.setState(0);
		dor.setPrice(jo.getInt("price"));
		dor.setTaketime(Timestamp.valueOf(jo.getString("taketime")));

		for(int i = 0; i < ja.length(); i++) {
			JSONObject tmp = (JSONObject)ja.get(i);
			DishMenu dm = new DishMenu();
			dm.setOrderid(orderid);
			dm.setWindow(Integer.parseInt((String) tmp.get("window")));
			dm.setDish(Integer.parseInt((String) tmp.get("dish")));
			dm.setNumber(Integer.parseInt((String) tmp.get("number")));
			dmlist.add(dm);
		}
		
		int oresult = orderService.addDishOrder(dor);
		if (oresult == 0) {
			return "fail";
		}
		int mresult = orderService.addDishMenu(dmlist);
		if(mresult == 0) {
			return "fail";
		}
		return orderid;
	}

	public static void main(String[] args) {
		Timestamp ts = Timestamp.valueOf("2017-05-02 16:14:02");
		System.out.println(ts);
	}
}
