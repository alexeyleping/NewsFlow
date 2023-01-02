package com.example.news.controllers;

import com.example.news.entity.SubjectMatter;
import com.example.news.service.SubjectMatterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjectmatter")
public class SubjectMatterController {
    private final SubjectMatterService subjectMatterService;

    public SubjectMatterController(SubjectMatterService subjectMatterService) {
        this.subjectMatterService = subjectMatterService;
    }

    @GetMapping("/get{id}")
    public SubjectMatter getSubjectMethod(@PathVariable Long id){
        return subjectMatterService.getSubjectMatter(id);
    }

    @GetMapping("/getall")
    public List<SubjectMatter> getAll(){
        return subjectMatterService.getAll();
    }

}
