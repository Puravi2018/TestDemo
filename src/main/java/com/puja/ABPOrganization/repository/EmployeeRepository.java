package com.puja.ABPOrganization.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puja.ABPOrganization.DTO.EmployeeDTO;
import com.puja.ABPOrganization.model.EmployeeEntity;
import com.puja.ABPOrganization.request.Address;
import com.puja.ABPOrganization.request.Employees;
import com.puja.ABPOrganization.request.NestedEmployee;
import com.puja.ABPOrganization.request.Skills;
import com.puja.ABPOrganization.response.EmployeeResponse;
@Repository
public class EmployeeRepository {
	
	private static JdbcTemplate jdbcTemplate;
	
	@Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public int insertEmployee(String firstname, String lastname, int age, String email, String phone, int deptId, int posId, int skillId) {
		
		System.out.println("I am inside Repository class 1");
		
		String insertQuery = "INSERT INTO employees (firstname, lastname, age, email, phone, dept_Id, pos_Id, skill_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println("I am inside Repository class 2");
		
		return jdbcTemplate.update(insertQuery,firstname,lastname, age, email, phone, deptId, posId, skillId);
	}

	public int insertTwoEmployees(String firstname, String lastname, int age, String email, String phone, int deptId,int posId, int skillId) {
		
		System.out.println("I am inside Repository class 1");
		
        String insertQuery = "INSERT INTO employees (firstname, lastname, age, email, phone, dept_Id, pos_Id, skill_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println("I am inside Repository class 2");
		
		return jdbcTemplate.update(insertQuery,firstname,lastname, age, email, phone, deptId, posId, skillId);		
	}

	public void deleteEmployee(int empid) {
		
		System.out.println("I am inside Repository class 1");
		
		String deleteQuery1 = "DELETE from employees where emp_id = ?";
		
		String deleteQuery2 = "DELETE from addresses where emp_id = ?";
		
		System.out.println("I am inside Repository class 2");
		//first we will run deletequery2 as there is dependency on deletequer1
		jdbcTemplate.update(deleteQuery2, empid);
		jdbcTemplate.update(deleteQuery1, empid);
		
	}
	
	public String insertNestedEmployee(NestedEmployee nemp) {
		
		
          //insertSkill(nemp.getSkills());
          insertAddress(nemp.getAddress());
          insertEmployee(nemp);

          return "Done";
		
		/*System.out.println("I am inside Repository class");
		
		String insertSkills = "insert into skills(skill_name)values(?)";
		
		String insertAddress = "insert into addresses(street, city, state, zip)values(?, ?, ?, ?)";
		
		String insertNestedEmployee = "Insert into employees(firstname, lastname, email, phone)values(?, ?, ?, ?)";
		
		
		jdbcTemplate.update(insertSkills, nemp.getSkills());
		jdbcTemplate.update(insertAddress, nemp.getAddress().getStreet(),nemp.getAddress().getCity(), nemp.getAddress().getState(), nemp.getAddress().getZipCode());
		jdbcTemplate.update(insertNestedEmployee, nemp.getFirstname(), nemp.getLastname(), nemp.getEmail(), nemp.getPhone());
		
		*/
	}

	private void insertAddress(Address address) {
		
		System.out.println("Inside insertAddress method");
		
		String insertAddress = "insert into addresses(street, city, state, zip)values(?, ?, ?, ?)";
		jdbcTemplate.update(insertAddress, address.getStreet(),address.getCity(), address.getState(), address.getZipCode());
		
	}

	private void insertEmployee(NestedEmployee nemp) {
		
		String insertNestedEmployee = "Insert into employees(firstname, lastname, email, phone, skill_id)values(?, ?, ?, ?, (select skill_id from public.skills where skill_name = 'Docker'))";
		
		jdbcTemplate.update(insertNestedEmployee, nemp.getFirstname(), nemp.getLastname(), nemp.getEmail(), nemp.getPhone());
	}

	private void insertSkill(List<Skills> skills) {
		
		System.out.println("List = " + skills);
		
		for(Skills s : skills)
		{
			System.out.println("This is s.getname: " + s.getName());
			String insertSkills = "insert into skills(skill_name)values(?)";
			
			jdbcTemplate.update(insertSkills, s.getName());
		}
	}

	public EmployeeEntity getEmployeeDetailsRep(Employees emp) {
		
		System.out.println("I am inside Repsitory Class");
		String getQuery = "Select * from employees where firstname = ?";
		
		EmployeeEntity employee = jdbcTemplate.queryForObject(
				getQuery,
			    new Object[]{emp.getFirstname()},
			    new BeanPropertyRowMapper<>(EmployeeEntity.class)
			);
		
		System.out.println("result from DB : " + employee);
		
		return employee;
	}
	
	public EmployeeResponse getEmployeeByIdAndNameRep(Employees emp) {
		
		System.out.println("I am inside Repsitory Class");
		String getQuery = "Select firstname, lastname from employees where emp_id = ? and lastname = ?";
		
		EmployeeEntity emp1 = jdbcTemplate.queryForObject(getQuery, new Object[] {emp.getEmp_id(), emp.getLastname()}, new BeanPropertyRowMapper<>(EmployeeEntity.class));
		
		System.out.println("result from DB : " + emp1);
		
        return createemployeeResponseData(emp1);
		
	}

	private EmployeeResponse createemployeeResponseData(EmployeeEntity emp1) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
        String reversedFirstname = new StringBuilder(emp1.getFirstname()).reverse().toString();
		
		employeeResponse.setFirstname(reversedFirstname);
		employeeResponse.setLastname(emp1.getLastname());
		employeeResponse.setJob('Y'); 
		return employeeResponse;
	}

	public List<EmployeeResponse> getListEmployeeRep(List<Employees> emp) {
			
		System.out.println("I am inside Repsitory Class");
		String getQuery = "Select firstname, lastname from employees where firstname in (?, ?)";
		
		List<EmployeeEntity> emp1 = jdbcTemplate.query(getQuery, new Object[]{emp.get(0).getFirstname(), emp.get(1).getFirstname()}, new BeanPropertyRowMapper<>(EmployeeEntity.class));
		
		System.out.println("result from DB : " + emp1);
		
		return createEmpListByFirstname(emp1);
	}
		
	private List<EmployeeResponse> createEmpListByFirstname(List<EmployeeEntity> emp1) {
		
      List<EmployeeResponse> employeeResponses = new ArrayList<EmployeeResponse>();
		
		for(EmployeeEntity E: emp1) {
		
			EmployeeResponse employeeResponse = new EmployeeResponse();
			
			employeeResponse.setFirstname(E.getFirstname());
	        employeeResponse.setLastname(E.getLastname());
	        employeeResponse.setJob('Y');
	        
	        employeeResponses.add(employeeResponse);
		
		}
		Collections.reverse(employeeResponses);
		
		return employeeResponses;
	}

	public String getTwoEmployeeDetailsRep(List<NestedEmployee> nemp) {
		
		System.out.println("I am inside Repsitory Class");
		System.out.println("I have reached to Repository class = " + nemp);
		
		String getQuery = "select employees.firstname, skills.skill_name \n"
				+ "from employees join skills \n"
				+ "on employees.skill_id = skills.skill_id \n"
				+ "where firstname in(?) and skill_name in (?)";
		
		List<EmployeeEntity> employeeEntityList = new ArrayList<>();
		
		for(NestedEmployee np: nemp) {
			
			System.out.println("I am going inside the loop");
			EmployeeEntity listEmp = jdbcTemplate.queryForObject(getQuery, new Object[] {np.getFirstname(), np.getSkills().getName()},new BeanPropertyRowMapper<>(EmployeeEntity.class));		
			
			System.out.println(listEmp);
			
			employeeEntityList.add(listEmp);
			
			System.out.println("I am going outside the loop");
			
		}
		
		System.out.println(employeeEntityList);
		
		return "Hello";
	}

	public List<EmployeeDTO> getEmployeeAddressRepository(List<Employees> emp) {
		
		System.out.println("I am inside Repsitory Class");
		System.out.println("I have reached to Repository class = " + emp);
		
		String getQuery = "select employees.emp_id,employees.firstname, addresses.city, addresses.state, skills.skill_name,positions.pos_name\n"
				+ "from employees join addresses on employees.emp_id = addresses.emp_id\n"
				+ "               join positions   on employees.pos_id = positions.pos_id\n"
				+ "			   join skills on employees.skill_id = skills.skill_id\n"
				+ "where firstname = ?";
		
		List<EmployeeDTO> empdto = new ArrayList<>();
		
		for(Employees empD : emp) {
			
			System.out.println("I am going inside the loop");
			EmployeeDTO empDto = jdbcTemplate.queryForObject(getQuery, new Object[] {empD.getFirstname()}, new BeanPropertyRowMapper<>(EmployeeDTO.class));
			
			empdto.add(empDto);
			System.out.println("I am going outside the loop");
			
		}
		
		System.out.println(empdto);
		
		List<EmployeeDTO> e =filterEmily(empdto);
		
		return e;
	}

	private List<EmployeeDTO> filterEmily(List<EmployeeDTO> empdto) {
	
	List<EmployeeDTO> filterEm = new ArrayList<>();
	
		for(EmployeeDTO filter: empdto) {
			
			if("Emily".equals(filter.getFirstname())) {
				System.out.println("Found Emily");
			}
				else {
					filterEm.add(filter);
				}
					
				
			}
		
		return filterEm;
		
	}
		
	

}





































