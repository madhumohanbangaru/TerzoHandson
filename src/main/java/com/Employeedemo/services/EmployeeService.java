package com.Employeedemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employeedemo.models.Department;
import com.Employeedemo.models.Employee;
import com.Employeedemo.models.Employee.Designation;
import com.Employeedemo.repositories.DepartmentRepository;
import com.Employeedemo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee getById(Integer id) {
		return employeeRepository.getById(id);
	}
	
	

	public Employee saveAndFlush(Employee employee) {
		return employeeRepository.saveAndFlush(employee);
	}

	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
		
	}
	public Optional<Department> findByDepId(int id){
		Optional<Department> result = departmentRepository.findById(id);
		if(result.isPresent())
			return result;
		else
			return null;
	}
	
	/*
	 * public List<Employee> findEmpBySal(double salary){ List<Employee>
	 * empList=employeeRepository.findAll(); return
	 * empList.stream().filter(e->e.getEmp_salary()>salary).collect(Collectors.
	 * toList()); }
	 */

	/*
	 * public List<Employee> findByTitle(Designation checkRole) { List<Employee>
	 * empList=employeeRepository.findAll(); return
	 * empList.stream().filter(e->e.getDesignation().equals(checkRole)).collect(
	 * Collectors.toList()); }
	 */
	
	public List<Employee> findEmpBySal(double salary){
		return  employeeRepository.findAll(EmployeeSpecificationService.hasSalaryAbove(salary,employeeRepository));
	}

	public List<Employee> findEmpBySalary(Set<Employee>e,double salary){
		List<String> empIDList = new ArrayList<String>();
		for(Employee emp :e)
			empIDList.add(String.valueOf(emp.getEmp_id()));
		return employeeRepository.findAll(EmployeeSpecificationService.hasSalaryAbove(salary,empIDList));
	}
	public List<Employee> findByTitle(Designation title){
		return  employeeRepository.findAll(EmployeeSpecificationService.hasEmp_Id(employeeRepository).and(EmployeeSpecificationService.findByTitle(title)));
	}
}
