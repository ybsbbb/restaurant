package cn.edu.bjtu.yb.restaurant.util;

import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单生成器
 * @author 杨博
 *
 */
@Component
public class OrderGeneration {

	private int num;
	
	/**
	 * 生成订单号，格式yyyyMMddHHmmssdddd，dddd为该订单在当天的序号
	 * @return
	 */
	public String genOrderNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(System.currentTimeMillis());
		String order = time + String.format("%04d", num++);
		return order;
	}
	
	/**
	 * 定时器，每天00:00清零
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	private void tozero() {
		num = 0;
	}
	
	public static void main(String[] args) {
	}
}
