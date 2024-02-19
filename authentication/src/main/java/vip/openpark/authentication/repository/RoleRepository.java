package vip.openpark.authentication.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import vip.openpark.authentication.domain.RoleEntity;

/**
 * @author anthony
 * @version 2024/2/19 13:29
 */
@Repository
public interface RoleRepository extends R2dbcRepository<RoleEntity, Long> {
}