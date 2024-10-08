package com.report.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    
}
