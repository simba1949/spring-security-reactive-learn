package vip.openpark.authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

/**
 * 自定义加载用户信息的服务
 *
 * @author anthony
 * @version 2024/2/19 15:12
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {
	private final DatabaseClient databaseClient;
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return databaseClient
			       .sql("SELECT  " +
				            "u.*, " +
				            "r.code role_code, " +
				            "p.url p_url " +
				            "FROM user u " +
				            "LEFT JOIN user_role ur ON u.id = ur.user_id  " +
				            "LEFT JOIN role r ON r.id  = ur.role_id  " +
				            "LEFT JOIN role_permission rp ON rp.role_id = r.id  " +
				            "LEFT JOIN permission p ON p.id = rp.permission_id  " +
				            "WHERE u.username = ?;")
			       .bind(0, username)
			       .fetch()
			       .all()
			       // 根据用户名进行分组
			       .bufferUntilChanged(ele -> ele.get("username"))
			       .map(dataList -> {
				       String usernameFromDB = dataList.stream().findFirst().map(ele -> ele.get("username")).orElseThrow(() -> new RuntimeException("username not found")).toString();
				       String encodePasswordFromDB =
					       dataList
						       .stream()
						       .findFirst()
						       .map(ele -> {
							       String passwordFromDB = ele.get("password").toString();
							       // 【不推荐】因为数据库的密码没有加密，这里加载后先加密在交给 spring security reactive 处理
							       return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(passwordFromDB);
						       })
						       .orElseThrow(() -> new RuntimeException("username not found"));
				       
				       // 用户的角色信息，用户角色信息变成 GrantedAuthority 时一定要加 ROLE_ 前缀，用于 spring security 识别
				       List<SimpleGrantedAuthority> roleAuthorityList =
					       dataList.stream()
						       .map(ele -> "ROLE_" + ele.get("role_code").toString())
						       .distinct()
						       .map(SimpleGrantedAuthority::new)
						       .toList();
				       // 用户的权限信息
				       List<SimpleGrantedAuthority> permissionAuthorityList =
					       dataList.stream()
						       .map(ele -> ele.get("p_url").toString())
						       .distinct()
						       .map(SimpleGrantedAuthority::new)
						       .toList();
				       SimpleGrantedAuthority[] roleAndPermissionAuthorityArray =
					       Stream.of(roleAuthorityList, permissionAuthorityList)
						       .flatMap(List::stream)
						       .toArray(SimpleGrantedAuthority[]::new);
				       
				       return User.builder()
					              .username(usernameFromDB)
					              .password(encodePasswordFromDB)
					              // 【注意】.roles() 和 .authorities() 方法只能二选一，后一个会覆盖前一个
					              .authorities(roleAndPermissionAuthorityArray)
					              .build();
			       })
			       .last();
	}
}