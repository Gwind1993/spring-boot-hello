package com.spring.boot.example.dao;

import com.spring.boot.example.ben.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {



}
