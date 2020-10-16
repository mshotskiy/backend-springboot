package com.shotskyi.tasklist.backendspringboot.repo;

import com.shotskyi.tasklist.backendspringboot.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

}
