package com.shotskyi.tasklist.backendspringboot.controller;

import com.shotskyi.tasklist.backendspringboot.entity.PriorityEntity;
import com.shotskyi.tasklist.backendspringboot.model.PrioritySearch;
import com.shotskyi.tasklist.backendspringboot.repo.PriorityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<PriorityEntity> findPriorityById(@PathVariable Long id) {
        Optional<PriorityEntity> priorityEntityOptional = priorityRepository.findById(id);
        if (priorityEntityOptional.isPresent()) {
            return ResponseEntity.ok(priorityEntityOptional.get());
        } else {
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/all")
    public List<PriorityEntity> findAllPriority(){
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/find")
    public List<PriorityEntity> findByParams(@RequestBody PrioritySearch prioritySearch){
        return priorityRepository.findAllByTitle(prioritySearch.getTitle());
    }

    @PostMapping("/add")
    public ResponseEntity<PriorityEntity> addPriority(@RequestBody PriorityEntity priorityEntity) {
        if (priorityEntity.getId() != null && priorityEntity.getId() != 0) {
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priorityEntity.getTitle() == null || priorityEntity.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priorityEntity.getColor() == null || priorityEntity.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priorityEntity));
    }

    @PutMapping("/update")
    public ResponseEntity<PriorityEntity> updatePriority(@RequestBody PriorityEntity priorityEntity) {
        if (priorityEntity.getId() == null || priorityEntity.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priorityEntity.getTitle() == null || priorityEntity.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priorityEntity.getColor() == null || priorityEntity.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: co;or", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priorityEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PriorityEntity> deletePriorityById(@PathVariable Long id) {

        try {
            priorityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
