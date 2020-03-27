package com.spring.boot.example.dao;

import com.spring.boot.example.ben.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department,Integer> {
}
