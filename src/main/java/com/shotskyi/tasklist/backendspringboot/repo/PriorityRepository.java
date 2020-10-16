package com.shotskyi.tasklist.backendspringboot.repo;

import com.shotskyi.tasklist.backendspringboot.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
}
