package com.Employeedemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Employeedemo.models.Department;
import com.Employeedemo.models.Employee;
import com.Employeedemo.models.Employee.Designation;

import com.Employeedemo.services.DepartmentService;
import com.Employeedemo.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/Employee")
public class EmployeeController {
	
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	public List<Employee> list(){
		return employeeService.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Employee get(@PathVariable Integer id){
		return employeeService.getById(id);
	}
	
	@PostMapping
	public Employee create(@RequestBody final Employee employee) {
		return employeeService.saveAndFlush(employee);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		employeeService.deleteById(id);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public Employee update(@PathVariable Integer id,@RequestBody Employee employee){
		Employee existingemp=employeeService.getById(id);
		BeanUtils.copyProperties(employee, existingemp, "emp_id");
		return employeeService.saveAndFlush(existingemp);
	}
	@GetMapping(path="/getEmpByDeptID")
	@ResponseBody
	private ResponseEntity<Object> findById(@RequestParam("	") int deptID){
		Optional<Department> result = employeeService.findByDepId(deptID);
		
			return new ResponseEntity<Object>(result.get().getEmployees(),HttpStatus.OK);
		
		}
	
	@GetMapping(path="/getEmpBySalary")
	@ResponseBody
	private ResponseEntity<Object> findEmpBySal(@RequestParam("sal") double sal){
		List<Employee> emp = employeeService.findEmpBySal(sal);
			return new ResponseEntity<Object>(emp,HttpStatus.OK);
	}
	
	@GetMapping(path="/getEmpbyRole")
	@ResponseBody
	private ResponseEntity<Object> getEmpbyRole(@RequestParam("title") String designation){
		List<Employee> emp = employeeService.findByTitle(checkRole(null!=designation && designation.length()>0?designation.toUpperCase():null));
		
		return new ResponseEntity<Object>(emp,HttpStatus.OK);
			}
	
	private Designation checkRole(String designation) {
		Designation[] role = Designation.values();
		for(Designation d: role) {
			if(d.toString().equals(designation))
				return d;
		}
		return null;
	}
	
	  @GetMapping(path="/getEmpinDeptBySalary")
	  
	  @ResponseBody private ResponseEntity<Object>getEmpinDeptBySalary(@RequestParam("deptID") int deptID,@RequestParam("sal")double sal)
	  { 
		  List<Employee> emp = null; 
		  Department result =departmentService.getById(deptID); 
	  if(result!=null) 
	  { 
		  emp =employeeService.findEmpBySalary(result.getEmployees(), sal); 
		  return new ResponseEntity<Object>(emp,HttpStatus.OK); 
		  } 
	  else
		  return null;
	  
	  }
	 
	 
}
