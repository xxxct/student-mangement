package com.example.demo.repository;

import com.example.demo.entity.Fee;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FeeRepository extends JpaRepository<Fee,Integer> {
    public Collection<Fee> findByInstitute(String institute);
    public Fee findById(int id);
}
