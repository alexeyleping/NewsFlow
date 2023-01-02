package com.example.news.controllers;

import com.example.news.entity.SubjectMatter;
import com.example.news.service.SubjectMatterService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjectmatter")
public class SubjectController {
    private final SubjectMatterService subjectMatterService;

    public SubjectController(SubjectMatterService subjectMatterService) {
        this.subjectMatterService = subjectMatterService;
    }

    @GetMapping("/{id}")
    public SubjectMatter getSubjectMethod(@PathVariable Long id){
        return subjectMatterService.getSubjectMatter(id);
    }

    @GetMapping("/getall")
    public Page<SubjectMatter> getAll(@RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page){
        return subjectMatterService.getAll(page, limit);
    }

}
