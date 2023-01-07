package com.example.news.controllers;

import com.example.news.entity.Subject;
import com.example.news.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public Subject getSubjectMethod(@PathVariable Long id){
        return subjectService.getSubjectMatter(id);
    }

    @GetMapping
    public Page<Subject> getAll(@RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                @RequestParam(value = "page", defaultValue = "0") Integer page){
        return subjectService.getAll(page, limit);
    }

}
