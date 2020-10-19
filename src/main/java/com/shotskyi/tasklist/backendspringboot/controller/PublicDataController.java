package com.shotskyi.tasklist.backendspringboot.controller;

import com.shotskyi.tasklist.backendspringboot.entity.CategoryEntity;
import com.shotskyi.tasklist.backendspringboot.repo.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity categoryEntity){
        if (categoryEntity.getId() != null && categoryEntity.getId() == 0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (categoryEntity.getTitle() == null || categoryEntity.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(repository.save(categoryEntity));
    }
}
