package cn.edu.bjtu.yb.restaurant.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>spring boot的一些配置
 * <p>目前的配置是关于静态资源(图片)的配置
 * @author yb775
 *
 */
@Configuration
public class ResourcesConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/restaurant/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/RESTAURANT/");
		registry.addResourceHandler("/img/dish/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/DISH/");
		registry.addResourceHandler("/img/avatar/**").addResourceLocations("file:///D:/RESOURCES/STATIC/IMG/AVATAR/");
	}
}
