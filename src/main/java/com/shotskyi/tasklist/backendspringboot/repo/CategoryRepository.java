package com.shotskyi.tasklist.backendspringboot.repo;

import com.shotskyi.tasklist.backendspringboot.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    List<CategoryEntity> findAllByOrderByTitleAsc();

    @Query("SELECT c FROM CategoryEntity c WHERE " +
            "(:title is null or :title='' or lower(c.title) like lower(concat('%', :title, '%'))) " +
            "order by c.title asc")
    List<CategoryEntity> findByTitle(@Param("title") String title);

}
