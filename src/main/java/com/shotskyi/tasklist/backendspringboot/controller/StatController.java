package com.shotskyi.tasklist.backendspringboot.controller;

import com.shotskyi.tasklist.backendspringboot.entity.StatEntity;
import com.shotskyi.tasklist.backendspringboot.repo.StatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatController {
    private StatRepository statRepository;
    private final Long defaultId = 1L;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping()
    public StatEntity getStat(){
        return statRepository.findById(defaultId).get();
    }
}
