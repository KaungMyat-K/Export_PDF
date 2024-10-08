package com.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.entity.Student;
import com.report.repo.StudentRepo;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
}
