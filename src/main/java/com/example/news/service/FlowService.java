package com.example.news.service;

import com.example.news.entity.Flow;
import com.example.news.entity.Subject;
import com.example.news.repository.FlowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FlowService {
    private final FlowRepository flowRepository;

    public FlowService(FlowRepository flowRepository) {
        this.flowRepository = flowRepository;
    }

    public Flow getFlow(Long id) {
        return flowRepository.getReferenceById(id);
    }

    public Page<Flow> getFlowAnyParams(Long source, Long subjectMatter, Integer limit, Integer page) {
        Pageable pageable = PageRequest.of(page, limit);
        return flowRepository.findAllBySourceIdOrSubjectId(source, subjectMatter, pageable);
    }

    public Long countBySource(Long source) {
        return flowRepository.countFlowBySourceId(source);
    }

    public List<Flow> findAllBySourceId(Long id){
       return flowRepository.findAllBySourceId(id);
    }
}
