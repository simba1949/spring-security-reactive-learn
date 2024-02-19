package vip.openpark.authorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author anthony
 * @version 2024/2/19 16:23
 */
@RestController
@RequestMapping("user")
public class UserController {
	@RequestMapping("role")
	@PreAuthorize("hasRole('user')")
	public Mono<String> role() {
		return Mono.just("user_role");
	}
	
	@RequestMapping("permission")
	@PreAuthorize("hasAuthority('/page/role/select')")
	public Mono<String> permission() {
		return Mono.just("user_permission");
	}
}