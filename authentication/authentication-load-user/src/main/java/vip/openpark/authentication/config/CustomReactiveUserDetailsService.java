package vip.openpark.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vip.openpark.authentication.domain.UserEntity;
import vip.openpark.authentication.repository.UserRepository;

import java.util.Collections;

/**
 * 自定义加载用户信息的服务
 *
 * @author anthony
 * @version 2024/2/19 15:12
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		return userRepository
			       .findOne(Example.of(userEntity))
			       .map(eleUserEntity -> {
				       // 【不推荐】因为数据库的密码没有加密，这里加载后先加密在交给 spring security reactive 处理
				       String encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(eleUserEntity.getPassword());
				       return new User(
					       eleUserEntity.getUsername(), encodePassword,
					       true, true, true, true,
					       Collections.emptyList());
			       });
	}
}