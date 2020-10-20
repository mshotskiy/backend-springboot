package com.shotskyi.tasklist.backendspringboot.repo;

import com.shotskyi.tasklist.backendspringboot.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
    List<PriorityEntity> findAllByOrderByIdAsc();

    @Query("SELECT p FROM PriorityEntity p " +
            "WHERE (:title='' or :title is null or lower(p.title) like lower(concat('%',:title,'%'))) " +
            "order by p.title asc")
    List<PriorityEntity> findAllByTitle(@Param("title") String title);
}
