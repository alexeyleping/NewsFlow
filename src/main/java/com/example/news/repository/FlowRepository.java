package com.example.news.repository;

import com.example.news.entity.Flow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowRepository extends JpaRepository<Flow, Long> {
    Page<Flow> findAllBySourceIdOrSubjectMatterId(Long source, Long subjectMatter, Pageable pageable);
}
