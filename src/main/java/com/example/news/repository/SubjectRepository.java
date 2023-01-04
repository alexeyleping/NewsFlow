package com.example.news.repository;

import com.example.news.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
