package com.example.news.service;

import com.example.news.entity.Source;
import com.example.news.repository.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SourceService {
    private final SourceRepository sourceRepository;

    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public Source getSource(Long id){
        return sourceRepository.getReferenceById(id);
    }

    public Page<Source> getAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page, limit);
        return sourceRepository.findAll(pageable);
    }

    public List<Source> getAll(){
        return sourceRepository.findAll();
    }
}
