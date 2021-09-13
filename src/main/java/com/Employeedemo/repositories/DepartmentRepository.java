package com.Employeedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employeedemo.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
