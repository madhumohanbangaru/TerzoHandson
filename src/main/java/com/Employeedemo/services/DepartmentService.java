package com.Employeedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employeedemo.models.Department;
import com.Employeedemo.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department getById(Integer id) {
		return departmentRepository.getById(id);
	}

	public Department saveAndFlush(Department department) {
		return departmentRepository.saveAndFlush(department);
	}

	public void deleteById(Integer id) {
		departmentRepository.deleteById(id);
		
	}

	
}
