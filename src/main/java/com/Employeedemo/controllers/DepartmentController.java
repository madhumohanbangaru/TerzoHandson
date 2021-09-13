package com.Employeedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Employeedemo.models.Department;
import com.Employeedemo.services.DepartmentService;

@RestController
@RequestMapping("/api/v1/Department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
		
	@GetMapping
	public List<Department> list(){
		return departmentService.findAll();
	}
		
	@GetMapping
	@RequestMapping("{id}")
	public Department get(@PathVariable Integer id){
		return departmentService.getById(id);
	}

	@PostMapping
	public Department create(@RequestBody final Department department) {
		return departmentService.saveAndFlush(department);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		departmentService.deleteById(id);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public Department update(@PathVariable Integer id,@RequestBody Department dept){
		Department existingdept=departmentService.getById(id);
		BeanUtils.copyProperties(dept, existingdept, "dept_id");
		return departmentService.saveAndFlush(existingdept);
	}
	
}
