package com.Employeedemo.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
@Data
@Entity(name="Department")
@JsonIgnoreProperties({"hibernateLazyInitializer","employees"})
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int dept_id;
	private String dept_name;
	
	  @OneToMany(mappedBy="department",cascade=CascadeType.ALL,fetch =
	  FetchType.LAZY)
	  
	  @JsonManagedReference private Set<Employee> employees = new
	  HashSet<Employee>(0);
	 
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public Department() {
		super();
	}
	
	  public Set<Employee> getEmployees() { return employees; } public void
	  setEmployees(Set<Employee> employees) { this.employees = employees; }
	 
}
