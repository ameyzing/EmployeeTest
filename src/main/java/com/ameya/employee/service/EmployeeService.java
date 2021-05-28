package com.ameya.employee.service;

import java.util.List;

import com.ameya.employee.model.EmployeeBean;

public interface EmployeeService {
	
	EmployeeBean getEmployee(Long id);
	EmployeeBean deleteEmployee(Long id);
	EmployeeBean addEmployee(EmployeeBean employeeBean);
	EmployeeBean updateEmployee(EmployeeBean employeeBean);
	List<EmployeeBean> getEmployeesOfMinSalary(Integer salary);
	List<EmployeeBean> getEmployeesOfDepartment( String department);

}
