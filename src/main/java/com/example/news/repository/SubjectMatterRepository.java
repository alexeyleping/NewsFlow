package com.example.news.repository;

import com.example.news.entity.SubjectMatter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMatterRepository extends JpaRepository<SubjectMatter, Long> {
}
