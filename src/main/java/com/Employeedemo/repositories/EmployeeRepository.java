package com.Employeedemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Employeedemo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>,JpaSpecificationExecutor<Employee>{
	
		@Query(value = "SELECT E.EMP_ID FROM EMPLOYEE E",nativeQuery = true)
		public List<String> fetchID();

	}


