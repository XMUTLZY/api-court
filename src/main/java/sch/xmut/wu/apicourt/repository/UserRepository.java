package sch.xmut.wu.apicourt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.wu.apicourt.entity.UserEntity;

/**
 * Created by wu on 2020/04/13
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
