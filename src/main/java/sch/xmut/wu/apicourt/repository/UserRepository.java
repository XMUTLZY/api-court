package sch.xmut.wu.apicourt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.wu.apicourt.entity.UserEntity;

import java.util.List;

/**
 * Created by wu on 2020/04/13
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "select * from user u where u.user_name like concat('%', :userName,'%') and u.wechat_number " +
            "like concat('%', :wechatNumber, '%')", nativeQuery = true)
    List<UserEntity> findUser(@Param("userName") String userName, @Param("wechatNumber") String wechatNumber);
}
