package com.ameya.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ameya.employee.model.EmployeeBean;
import com.ameya.employee.service.EmployeeService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {
//	@Mock
	private EmployeeService employeeService;
	
	@BeforeAll
	public void setup() {
		
		employeeService=mock(EmployeeService.class);
//		MockitoAnnotations.initMocks(this);
		Map<Long, EmployeeBean> allEmployee = new HashMap<>();
		
		EmployeeBean e1 = new EmployeeBean();
		EmployeeBean e2 = new EmployeeBean();
		EmployeeBean e3 = new EmployeeBean();
		EmployeeBean e4 = new EmployeeBean();
		EmployeeBean e5 = new EmployeeBean();
		e1.setId(1L);
		e1.setEmployeeName("Mark");
		e1.setDepartmentName("Java");
		e1.setSalary(2000);

		e2.setId(2L);
		e2.setEmployeeName("James");
		e2.setDepartmentName("Java");
		e2.setSalary(2200);

		e3.setId(3L);
		e3.setEmployeeName("Sergio");
		e3.setDepartmentName("Net");
		e3.setSalary(4000);

		e4.setId(4L);
		e4.setEmployeeName("Gareth");
		e4.setDepartmentName("Net");
		e4.setSalary(1000);

		e5.setId(5L);
		e5.setEmployeeName("Luka");
		e5.setDepartmentName("Net");
		e5.setSalary(3400);

		allEmployee.put(e1.getId(), e1);
		allEmployee.put(e2.getId(), e2);
		allEmployee.put(e3.getId(), e3);
		allEmployee.put(e4.getId(), e4);
		allEmployee.put(e5.getId(), e5);
		when(employeeService.getEmployee(1L)).thenReturn(e1);
		when(employeeService.getEmployee(2L)).thenReturn(null);
		when(employeeService.getEmployeesOfMinSalary(2000)).thenReturn(allEmployee.entrySet().stream().filter(entry -> entry.getValue().getSalary() >= 2000)
				.map(e -> e.getValue()).collect(Collectors.toList()));
		when(employeeService.getEmployeesOfDepartment("Java")).thenReturn(allEmployee.entrySet().stream()
				.filter(entry -> entry.getValue().getDepartmentName().equalsIgnoreCase("Java"))
				.map(e -> e.getValue()).collect(Collectors.toList()));
	}
	
	

	@Test
	@DisplayName("Get Employee based on Id with valid Id")
	public void getEployeeTest_validId() {
		
		
		EmployeeBean resp=employeeService.getEmployee(1L);
		assertNotNull(resp);
		assertEquals("Mark", resp.getEmployeeName());
	}
	
	@Test
	@DisplayName("Get Employee based on Id with invalid Id")
	public void getEployeeTest_invalidId() {
		
		
		EmployeeBean resp=employeeService.getEmployee(2L);
		assertNull(resp);
	}
	
	@Test
	@DisplayName("Get Employee List based on Minimum Salary ")
	public void getEmployeesOfMinSalary() {
				
		List <EmployeeBean> resp=employeeService.getEmployeesOfMinSalary(2000);
		assertEquals(4, resp.size());
	}
	
	@Test
	@DisplayName("Get Employee List based on Department")
	public void getEmployeesOfDepartment() {
				
		List <EmployeeBean> resp=employeeService.getEmployeesOfDepartment("Java");
		assertEquals(2, resp.size());
	}


}
