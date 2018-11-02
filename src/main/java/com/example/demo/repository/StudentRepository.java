package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByStudentId(String studentId);
    public List<Student> findByInstitute(String institute);

    public List<Student> findByInstituteAndArrived(String institute, boolean b);
}
