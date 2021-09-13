package com.Employeedemo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
@Data
@Entity(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer","managers"})
public class Employee {
	@Id
	private int emp_id;
	private String emp_name;
	private double emp_salary;
	private int depmnt_id;
	private int mgr_id;

	public enum Designation{
		ASSOCIATE,
		ARCHITECT,
		MANAGER
	}
	
	@Enumerated(EnumType.STRING)
	private Designation designation;
	
	

	@JoinColumn(name="mgr_id",referencedColumnName="emp_id",nullable = false,insertable = false,updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference("manager")
	private Employee manager;
	
	@OneToMany(mappedBy="manager")
	@JsonManagedReference("managers")
	@JsonIgnore
	private Set<Employee> managers = new HashSet<Employee>(0);
	
	@JsonIgnore
	@JsonManagedReference
	public Set<Employee> getManagers() {
		return managers;
	}

	public void setManagers(Set<Employee> managers) {
		this.managers = managers;
	}
	
	
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  
	  @JoinColumn(name="depmnt_id", referencedColumnName="dept_id",nullable =
	  false,insertable = false,updatable = false)
	  
	  @JsonBackReference private Department department;
	 

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	

	public double getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(double emp_salary) {
		this.emp_salary = emp_salary;
	}

	

	public int getDepmnt_id() {
		return depmnt_id;
	}

	public void setDepmnt_id(int depmnt_id) {
		this.depmnt_id = depmnt_id;
	}

	public int getMgr_id() {
		return mgr_id;
	}

	public void setMgr_id(int mgr_id) {
		this.mgr_id = mgr_id;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	
	  public Department getDepartment() { return department; }
	  
	  public void setDepartment(Department department) { this.department =
	  department; }
	 
}
