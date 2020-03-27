package com.spring.boot.example.dao;

import com.spring.boot.example.ben.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author
 * @since
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
