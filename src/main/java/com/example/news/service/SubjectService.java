package com.example.news.service;

import com.example.news.entity.Subject;
import com.example.news.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectMatter(Long id) {
        return subjectRepository.getReferenceById(id);
    }

    public Page<Subject> getAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return subjectRepository.findAll(pageable);
    }
}
