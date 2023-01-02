package com.example.news.service;

import com.example.news.entity.Source;
import com.example.news.repository.SourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Source> getAll(){
        return sourceRepository.findAll();
    }


}
