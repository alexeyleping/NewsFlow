package com.example.news.service;

import com.example.news.entity.SubjectMatter;
import com.example.news.repository.SubjectMatterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectMatterService {
    private final SubjectMatterRepository subjectMatterRepository;

    public SubjectMatterService(SubjectMatterRepository subjectMatterRepository) {
        this.subjectMatterRepository = subjectMatterRepository;
    }

    public SubjectMatter getSubjectMatter(Long id) {
        return subjectMatterRepository.getReferenceById(id);
    }

    public List<SubjectMatter> getAll() {
        return subjectMatterRepository.findAll();
    }

}
