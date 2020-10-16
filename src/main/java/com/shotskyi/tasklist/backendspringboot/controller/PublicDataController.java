package com.shotskyi.tasklist.backendspringboot.controller;

import com.shotskyi.tasklist.backendspringboot.entity.CategoryEntity;
import com.shotskyi.tasklist.backendspringboot.repo.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class PublicDataController {

    private CategoryRepository repository;

    public PublicDataController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/category")
    public List<CategoryEntity> getCategory(){
        return repository.findAll();
    }
}
