package cn.edu.bjtu.yb.restaurant.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourcesConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/restaurant/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/RESTAURANT/");
		registry.addResourceHandler("/img/dish/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/DISH/");
		registry.addResourceHandler("/img/avatar/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/AVATAR/");
	}
}
