package cn.edu.bjtu.yb.restaurant.controller.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bjtu.yb.restaurant.bean.DishMenu;
import cn.edu.bjtu.yb.restaurant.bean.DishOrder;
import cn.edu.bjtu.yb.restaurant.service.OrderService;
import cn.edu.bjtu.yb.restaurant.util.OrderGeneration;

@RestController
public class OrderController {

	/**
	 * 订单号生成器
	 */
	@Autowired
	private OrderGeneration orderGeneration;

	/**
	 * 处理订单的service
	 */
	@Autowired
	private OrderService orderService;	
	
	/**
	 * 根据用户id查询用户订单
	 * @return 查询结果的JSON字符串
	 */
	@GetMapping("/orders/user/{userid}")//简略信息 id&price&ordertime
	public String getOrdersByUser(@PathParam("userid") String userid) {
		return null;
	}

	/**
	 * 根据餐厅id查询订单
	 * @return 查询结果的JSON字符串
	 */
	@GetMapping("/orders/restaurant/{restaurantid}")//简略信息 id&price&ordertime
	public String getOrdersByRestaurant(@PathParam("restaurantid") String restaurantid) {
		return null;
	}

	/**
	 * 根据订单号查询订单详情
	 * @return 订单详情的json字符串
	 */
	@GetMapping("/orders/order/{orderid}")//详细信息
	public String getOrdersById(@PathParam("orderid") String orderid) {
		return null;
	}
	
	/**
	 * <p>提交订单
	 * @param order 订单的JSON字符串,格式
	 * 	<p>eg:{"menu":[{"window":"1","dish":"1","number":"1"},{"window":"1","dish":"1","number":"1"}],"restaurant":"1","price":"1000","taketime":"2017-05-05 11:11:11"}
	 * @param username 提交此订单的用户id
	 * @return 订单号
	 */
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
