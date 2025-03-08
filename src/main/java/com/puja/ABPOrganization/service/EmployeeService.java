package com.puja.ABPOrganization.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.puja.ABPOrganization.DTO.EmployeeDTO;
import com.puja.ABPOrganization.model.EmployeeEntity;
import com.puja.ABPOrganization.repository.EmployeeRepository;
import com.puja.ABPOrganization.request.Employees;
import com.puja.ABPOrganization.request.NestedEmployee;
import com.puja.ABPOrganization.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public String saveEmployee(Employees emp) {
		
		System.out.println("I am inside Service class 1");
		
		employeeRepository.insertEmployee(emp.getFirstname(),emp.getLastname(),emp.getAge(),emp.getEmail(),emp.getPhone(),emp.getDeptId(),emp.getPosId(),emp.getSkillId());
		
		System.out.println("I am inside Service class 2");
		
		return "inserted";
	}

	public String save2Employee(List<Employees> emp) {
		
		System.out.println("I am inside Service class 1");
		for(Employees emps : emp) {
			
			boolean isValid = isValidEmployee(emps);
			
			if(isValid) {
			employeeRepository.insertTwoEmployees(emps.getFirstname(),emps.getLastname(),emps.getAge(),emps.getEmail(),emps.getPhone(),emps.getDeptId(),emps.getPosId(),emps.getSkillId());
			}
		}
        System.out.println("I am inside Service class 2");
		
		return "inserted";
	}
	public String deleteEmployee(int empid) {
		
		System.out.println("I am inside Service class 1");
		
		employeeRepository.deleteEmployee(empid);
		
		System.out.println("I am inside Service class 2");
		return "deleted";
	}

	private boolean isValidEmployee(Employees a) {
		
		int[] validDepartmentIds = {1,2,3,4,5};
		boolean isFlag = false;
		
		
		for (Integer i : validDepartmentIds) {
			if (a.getDeptId() == i) {
				
				System.out.println(" valid");
				isFlag = true;
					break;
				}
			else
				{
					System.out.println("in valid");
					
					isFlag = false;
				}
		}
		return isFlag;
		
	}
	public String saveNestedEmployee(NestedEmployee nemp) {
		
		System.out.println("I am inside Service Class");
		
		System.out.println("Nested Employee Details upto Service:" + nemp);
		
		return employeeRepository.insertNestedEmployee(nemp);
				
	}

	public EmployeeEntity getEmployeeDetails(Employees emp) {
		
		System.out.println("I am inside Service Class");
		System.out.println("I have reached to Service class = " + emp);
		
		return employeeRepository.getEmployeeDetailsRep(emp);
	}

	public EmployeeResponse getEmployeeDetailsByIdAndName(Employees emp) {
		
		System.out.println("I am inside Service Class");
		System.out.println("I have reached to Service class = " + emp);
		
		return employeeRepository.getEmployeeByIdAndNameRep(emp);
	}

	public List<EmployeeResponse> getListEmployeeService(List<Employees> emp) {
		
		System.out.println("I am inside Service Class");
		System.out.println("I have reached to Service class = " + emp);
		
		return employeeRepository.getListEmployeeRep(emp);
	}

	public String getTwoEmployeeDetails(List<NestedEmployee> nemp) {
		
		System.out.println("I am inside Service Class");
		System.out.println("I have reached to Service class = " + nemp);
		return employeeRepository.getTwoEmployeeDetailsRep(nemp);
	}

	public List<EmployeeDTO> getEmployeeAddressService(List<Employees> emp) {
		
		System.out.println("I am inside Service Class");
		System.out.println("I have reached to Service class = " + emp);
		
		return employeeRepository.getEmployeeAddressRepository(emp);
	}
}










































