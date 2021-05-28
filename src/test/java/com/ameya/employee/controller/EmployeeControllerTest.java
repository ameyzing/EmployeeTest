package com.ameya.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ameya.employee.model.EmployeeBean;
import com.ameya.employee.service.EmployeeService;

class EmployeeControllerTest {
	
	private EmployeeService employeeService= mock(EmployeeService.class);

	@Test
	@DisplayName("Get Employee based on Id with valid Id")
	void getEployeeTest_validId() {
		EmployeeBean emp= new EmployeeBean();
		emp.setId(1L);
		emp.setEmployeeName("Ameya");
		emp.setSalary(2000);
		when(employeeService.getEmployee(1L)).thenReturn(emp);
		EmployeeBean resp=employeeService.getEmployee(1L);
		assertNotNull(resp);
		assertEquals("Ameya", resp.getEmployeeName());
	}
	
	@Test
	@DisplayName("Get Employee based on Id with invalid Id")
	void getEployeeTest_invalidId() {
		EmployeeBean emp= new EmployeeBean();
		emp.setId(1L);
		emp.setEmployeeName("Ameya");
		emp.setSalary(2000);
		when(employeeService.getEmployee(2L)).thenReturn(null);
		EmployeeBean resp=employeeService.getEmployee(2L);
		assertNull(resp);
	}
	
	@Test
	@DisplayName("Get Employee based on Id with invalid Id")
	void getEmployeesOfMinSalary() {
		EmployeeBean emp= new EmployeeBean();
		emp.setId(1L);
		emp.setEmployeeName("Ameya");
		emp.setSalary(2000);
		when(employeeService.getEmployeesOfMinSalary(2000)).thenReturn(null);
		EmployeeBean resp=employeeService.getEmployee(2L);
		assertNull(resp);
	}


}
