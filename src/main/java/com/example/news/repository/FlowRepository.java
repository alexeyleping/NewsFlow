package com.example.news.repository;

import com.example.news.entity.Flow;
import com.example.news.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlowRepository extends JpaRepository<Flow, Long> {
    Page<Flow> findAllBySourceIdOrSubjectId(Long source, Long subjectMatter, Pageable pageable);
    Long countFlowBySourceId(Long source);
    List<Flow> findAllBySourceId(Long id);


}
