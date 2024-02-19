package vip.openpark.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author anthony
 * @version 2024/2/19 14:10
 */
@RestController
public class PingController {
	@GetMapping("ping")
	public Mono<String> ping() {
		return Mono.just("pong");
	}
}