package com.puja.ABPOrganization.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puja.ABPOrganization.DTO.EmployeeDTO;
import com.puja.ABPOrganization.model.EmployeeEntity;
import com.puja.ABPOrganization.request.Employees;
import com.puja.ABPOrganization.request.NestedEmployee;
import com.puja.ABPOrganization.response.EmployeeResponse;
import com.puja.ABPOrganization.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	//Single Entry from Request Body
	@PostMapping("/employees/add")
	public String addEmployee(@RequestBody Employees emp) {
		System.out.println("I am inside the Controller Class");
		return employeeService.saveEmployee(emp);
		
	}
	
	//2 Entry from Request Body
	@PostMapping("/twoemployees/add")
	public String add2Employee(@RequestBody List<Employees> emp) {
		System.out.println("I am inside the Controller Class");
		return employeeService.save2Employee(emp);
			
		}
	
	@DeleteMapping("/employee/delete/{empid}")
	public String deleteEmployee(@PathVariable int empid) {
		
		System.out.println("I am inside the Controller Class");
		
		return employeeService.deleteEmployee(empid);
		
	}	
	@PostMapping("/nestedemployees/add")
	public String addNestedEmployee(@RequestBody NestedEmployee nemp) {
		
		System.out.println("I am inside Controller Class");
		System.out.println("Nested Employee Details upto Controller:" + nemp);
		return employeeService.saveNestedEmployee(nemp);
				
	}
	
	@GetMapping("/getEmployee")
	public EmployeeEntity getEmployee(@RequestBody Employees emp) {
		
		System.out.println("Hello I am inside Controller Class");
		System.out.println("I have reached to controller class = " + emp);
		
		return employeeService.getEmployeeDetails(emp);
	}
	
	@GetMapping("getEmployeeByIdAndName")
	public EmployeeResponse getEmployeeByIDAndName(@RequestBody Employees emp) {
		
		System.out.println("I am inide Controller Class");
		System.out.println("I have reached to controller class = " + emp);
		
		return employeeService.getEmployeeDetailsByIdAndName(emp);
	}
	
	@GetMapping("getListEmployee")
	public List<EmployeeResponse> getListEmployee(@RequestBody List<Employees> emp) {
		
		System.out.println("I am inide Controller Class");
		System.out.println("I have reached to controller class = " + emp);
		
		return employeeService.getListEmployeeService(emp);
	}
	
	@GetMapping("/getTwoEmployeeDetails")
	public String getTwoEmployeeDetails(@RequestBody List<NestedEmployee> nemp) {
		
		System.out.println("I am inide Controller Class");
		System.out.println("I have reached to controller class = " + nemp);
		
		return employeeService.getTwoEmployeeDetails(nemp);
	}
	
	@GetMapping("/getEmployeeAddress")
	public List<EmployeeDTO> getEmployeeAddressController(@RequestBody List<Employees> emp) {
		
		System.out.println("I am inide Controller Class");
		System.out.println("I have reached to controller class = " + emp);
		
		return employeeService.getEmployeeAddressService(emp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
