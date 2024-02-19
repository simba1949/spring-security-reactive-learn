package vip.openpark.authorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author anthony
 * @version 2024/2/19 16:19
 */
@RestController
@RequestMapping("admin")
public class AdminController {
	
	@PreAuthorize("hasRole('admin')")
	@GetMapping("role")
	public Mono<String> role() {
		return Mono.just("admin_role");
	}
	
	@PreAuthorize("hasAuthority('/page/user')")
	@GetMapping("permission")
	public Mono<String> permission() {
		return Mono.just("admin_permission");
	}
}