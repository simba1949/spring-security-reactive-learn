package vip.openpark.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author anthony
 * @version 2024/2/19 13:15
 */
@Configuration
@EnableR2dbcRepositories(basePackages = {"vip.openpark.authentication.repository"})
public class R2dbcConfig {
}