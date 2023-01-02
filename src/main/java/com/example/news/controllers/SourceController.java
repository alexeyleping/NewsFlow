package com.example.news.controllers;

import com.example.news.entity.Source;
import com.example.news.service.SourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/get{id}")
    public Source getSource(@PathVariable Long id){
        return sourceService.getSource(id);
    }

    @GetMapping("/getall")
    public List<Source> getAll(){
        return sourceService.getAll();
    }
}
