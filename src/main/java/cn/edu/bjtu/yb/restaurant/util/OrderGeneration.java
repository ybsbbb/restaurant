package cn.edu.bjtu.yb.restaurant.util;

import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderGeneration {

	private int num;
	
	public String genOrderNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(System.currentTimeMillis());
		String order = time + String.format("%04d", num++);
		return order;
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	private void tozero() {
		num = 0;
	}
	
	public static void main(String[] args) {
	}
}
