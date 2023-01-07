package com.example.news.controllers;

import com.example.news.entity.Source;
import com.example.news.service.SourceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/source")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/{id}")
    public Source getSource(@PathVariable Long id){
        return sourceService.getSource(id);
    }

    @GetMapping
    public Page<Source> getAll(@RequestParam(value = "limit", defaultValue = "10") Integer limit,
                               @RequestParam(value = "page", defaultValue = "0") Integer page){
        return sourceService.getAll(page, limit);
    }
}
