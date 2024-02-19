package vip.openpark.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author anthony
 * @version 2024/2/19 14:27
 */
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/static/**")
			.addResourceLocations("/static", "classpath:/static/");
	}
}