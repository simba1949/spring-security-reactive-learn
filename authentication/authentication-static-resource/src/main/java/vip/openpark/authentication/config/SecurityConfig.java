package vip.openpark.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author anthony
 * @version 2024/2/19 14:03
 */
@Configuration
public class SecurityConfig {
	
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
			.formLogin(withDefaults());
		return http.build();
	}
}