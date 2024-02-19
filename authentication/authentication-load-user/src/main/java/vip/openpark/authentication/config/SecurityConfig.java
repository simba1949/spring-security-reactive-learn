package vip.openpark.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author anthony
 * @version 2024/2/19 14:03
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class SecurityConfig {
	private final ReactiveUserDetailsService reactiveUserDetailsService;
	
	/**
	 * 密码加密器
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
			.authorizeExchange(
				exchanges ->
					exchanges
						.pathMatchers("/static/**").permitAll()
						.anyExchange().authenticated()
			)
			.httpBasic(withDefaults())
			// form login
			.formLogin(withDefaults())
			// spring security reactive 底层使用 ReactiveAuthenticationManager 来完成认证
			// 将自定义的 ReactiveUserDetailsService 传过来，交给 UserDetailsRepositoryReactiveAuthenticationManager 进行认证
			.authenticationManager(new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService))
		;
		return http.build();
	}
}