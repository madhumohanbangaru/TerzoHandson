package com.Employeedemo.services;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.Employeedemo.models.Employee;
import com.Employeedemo.models.Employee.Designation;
import com.Employeedemo.repositories.EmployeeRepository;
public class EmployeeSpecificationService implements Specification<Employee>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public static Specification<Employee> findById(long id){
			return(root,query,criteriaBuilder)->{
				return(criteriaBuilder.equal(root.get("emp_id"), id));
			};
		}
	 

		@SuppressWarnings("serial")
		public static Specification<Employee> findByTitle(Designation id){
			return new Specification<Employee>() {
				public Predicate toPredicate(Root<Employee> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
					return criteriaBuilder.equal(root.get("designation"), id);
				}
			};
		}
	 
	 
	 public static Specification<Employee> hasEmp_Id(EmployeeRepository empRepo) {
			return(root,query,criteriaBuilder)->{
				CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("emp_id"));
				Predicate eq = processInCondition(in,empRepo.fetchID());
				return(criteriaBuilder.and(eq));
			};
		}
	 public static CriteriaBuilder.In<Long> processInCondition(CriteriaBuilder.In<Long> in,List<String> condition){
			for(int i=0;i<condition.size();i++)
				in.value(Long.valueOf(condition.get(i)));
			return in;
		}
	 
	 public static Specification<Employee> hasSalaryAbove(double salary,EmployeeRepository repo){
			return(root,query,criteriaBuilder)->{
				List<Predicate> predicates = new ArrayList<>();
				CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("emp_id"));
				predicates.add(processInCondition(in,repo.fetchID()));
				predicates.add(criteriaBuilder.greaterThan(root.get("emp_salary"), salary));
				query.orderBy(criteriaBuilder.asc(root.get("emp_name")));
				return(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			};
		}
	 
	 
	 public static Specification<Employee> hasSalaryAbove(double salary,List<String> emp){
			return(root,query,criteriaBuilder)->{
				List<Predicate> predicates = new ArrayList<>();
				query.orderBy(criteriaBuilder.desc(root.get("emp_salary")));
				CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("emp_id"));
				predicates.add(processInCondition(in,emp));
				predicates.add(criteriaBuilder.greaterThan(root.get("emp_salary"), salary));
				return(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			};
		}
	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
