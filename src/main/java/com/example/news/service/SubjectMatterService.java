package com.example.news.service;

import com.example.news.entity.SubjectMatter;
import com.example.news.repository.SubjectMatterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<SubjectMatter> getAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return subjectMatterRepository.findAll(pageable);
    }
}
