package com.shotskyi.tasklist.backendspringboot.repo;

import com.shotskyi.tasklist.backendspringboot.entity.StatEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatRepository extends CrudRepository<StatEntity, Long> {
}
