package com.example.news.repository;

import com.example.news.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SourceRepository extends JpaRepository<Source, Long> {
}
