package vip.openpark.authorization.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import vip.openpark.authorization.domain.UserEntity;

/**
 * @author anthony
 * @version 2024/2/19 13:28
 */
@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {
}