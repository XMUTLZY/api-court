package sch.xmut.wu.apicourt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.wu.apicourt.entity.UserCollectEntity;
import java.util.List;

/**
 * Created by wu on 2020/04/14
 */
public interface UserCollectRepository extends JpaRepository<UserCollectEntity, Integer> {
    List<UserCollectEntity> findAllByUserId(Integer userId);
}
