package com.shotskyi.tasklist.backendspringboot.controller;

import com.shotskyi.tasklist.backendspringboot.entity.CategoryEntity;
import com.shotskyi.tasklist.backendspringboot.model.CategorySearch;
import com.shotskyi.tasklist.backendspringboot.repo.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    @GetMapping("/all")
    public List<CategoryEntity> getCategory() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryEntity> findCategoryById(@PathVariable Long id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
        if (categoryEntityOptional.isPresent()) {
            return ResponseEntity.ok(categoryEntityOptional.get());
        } else {
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/find")
    public List<CategoryEntity> findByParams(@RequestBody CategorySearch categorySearch){
        return categoryRepository.findByTitle(categorySearch.getTitle());
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity categoryEntity) {
        if (categoryEntity.getId() != null && categoryEntity.getId() != 0) {
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (categoryEntity.getTitle() == null || categoryEntity.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }


    @PutMapping("/update")
    public ResponseEntity<CategoryEntity> updateCategory(@RequestBody CategoryEntity categoryEntity) {
        if (categoryEntity.getId() == null || categoryEntity.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        if (categoryEntity.getTitle() == null || categoryEntity.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryEntity> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
