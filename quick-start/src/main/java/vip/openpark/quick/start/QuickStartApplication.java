package vip.openpark.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <div>
 *     spring security 自动配置，所有请求都需要登录才能访问
 * </div>
 * <div>
 *      servlet 容器下自动配置 {@link SecurityAutoConfiguration}
 *      导入 {@link SecurityFilterChain} 组件：默认所有请求都需要登录才可以访问、默认登录页
 * </div>
 * <div>
 *     响应式容器下配置 {@link ReactiveSecurityAutoConfiguration}
 *     导入 ServerHttpSecurityConfiguration 配置：注解导入 ServerHttpSecurityConfiguration
 * </div>
 * <div>
 *     MethodSecurityAspectJAutoProxyRegistrar
 * </div>
 *
 * @author anthony
 * @version 2024/2/19 13:37
 */
@SpringBootApplication
public class QuickStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStartApplication.class, args);
	}
}