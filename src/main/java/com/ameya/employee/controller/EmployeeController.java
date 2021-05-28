package com.ameya.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameya.employee.model.EmployeeBean;
import com.ameya.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	
	EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> addEmployee(@RequestBody EmployeeBean employeeBean){
		
		employeeBean=employeeService.addEmployee(employeeBean);
		if(employeeBean==null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeBean);
		
	}
	
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> getEmployee(@PathVariable Long id){
		
		System.out.println(id);
		EmployeeBean employeeBean=employeeService.getEmployee(id);
		if(employeeBean==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeBean);
		
	}
	
	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable Long id){
		
		EmployeeBean employeeBean=employeeService.deleteEmployee(id);
		
		if(employeeBean==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	@GetMapping(value="/minSalary/{salary}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeBean>> getEmployeesOfMinSalary(@PathVariable Integer salary){
		
		List<EmployeeBean> employeeList=employeeService.getEmployeesOfMinSalary(salary);
		
		return ResponseEntity.ok(employeeList);
		
	}
	
	@PutMapping(value="/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<EmployeeBean> updateEmployee(@PathVariable Long  id,@RequestBody EmployeeBean employeeBean){
		
		
		if(!id.equals(employeeBean.getId()))
			employeeBean.setId(id);
		
		employeeBean=employeeService.updateEmployee(employeeBean);
		if(employeeBean==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeBean);
		
	}
	
	@GetMapping(value="/department/{department}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeBean>> getEmployeesOfDepartment(@PathVariable String department){
		
		List<EmployeeBean> employeeList=employeeService.getEmployeesOfDepartment(department);
		
		return ResponseEntity.ok(employeeList);
		
	}
	

}
