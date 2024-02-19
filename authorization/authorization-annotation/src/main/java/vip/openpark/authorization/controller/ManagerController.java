package vip.openpark.authorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author anthony
 * @version 2024/2/19 16:22
 */
@RestController
@RequestMapping("manager")
public class ManagerController {
	@RequestMapping("role")
	@PreAuthorize("hasRole('manager')")
	public Mono<String> role() {
		return Mono.just("manager_role");
	}
	
	@RequestMapping("permission")
	@PreAuthorize("hasAuthority('/page/role')")
	public Mono<String> permission() {
		return Mono.just("manager_permission");
	}
}