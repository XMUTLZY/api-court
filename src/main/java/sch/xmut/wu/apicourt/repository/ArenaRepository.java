package sch.xmut.wu.apicourt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sch.xmut.wu.apicourt.entity.ArenaEntity;
import java.util.List;
import java.util.Optional;

/**
 * Created by wu on 2020/04/13
 */
public interface ArenaRepository extends JpaRepository<ArenaEntity, Integer> {
    List<ArenaEntity> findAllByIsRecommend(Integer isRecommend);
    List<ArenaEntity> findAll();
    Optional<ArenaEntity> findById(Integer id);
    @Query(value = "select * from arena u where u.name like concat('%', :name,'%')", nativeQuery = true)
    List<ArenaEntity> findAllByNameLike(String name);
}
