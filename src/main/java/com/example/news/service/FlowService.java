package com.example.news.service;

import com.example.news.entity.Flow;
import com.example.news.repository.FlowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlowService {
    private final FlowRepository flowRepository;

    public FlowService(FlowRepository flowRepository) {
        this.flowRepository = flowRepository;
    }

    public Flow getFlow(Long id){
        return flowRepository.getReferenceById(id);
    }

    public Page<Flow> getFlowAnyParams(Long source, Long subjectMatter, Integer limit, Integer page){
        Pageable pageable = PageRequest.of(page, limit);
        return flowRepository.findAllBySourceIdOrSubjectMatterId(source, subjectMatter, pageable);
    }
}
