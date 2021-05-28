package com.ameya.employee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ameya.employee.model.EmployeeBean;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	Map<Long, EmployeeBean> allEmployee = new HashMap<>();

	 {
		
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
	}

	@Override
	public EmployeeBean getEmployee(Long id) {
		if (allEmployee.containsKey(id)) {

			return allEmployee.get(id);

		}
		return null;
	}

	@Override
	public EmployeeBean deleteEmployee(Long id) {
		if (allEmployee.containsKey(id)) {

			return allEmployee.remove(id);

		}
		return null;
	}

	@Override
	public EmployeeBean addEmployee(EmployeeBean employeeBean) {

		if (allEmployee.containsKey(employeeBean.getId())) {
			return null;
		}

		allEmployee.put(employeeBean.getId(), employeeBean);

		return employeeBean;
	}

	@Override
	public List<EmployeeBean> getEmployeesOfMinSalary(Integer salary) {

		return allEmployee.entrySet().stream().filter(entry -> entry.getValue().getSalary() >= salary)
				.map(e -> e.getValue()).collect(Collectors.toList());

	}

	@Override
	public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
		if (allEmployee.containsKey(employeeBean.getId())) {
			
			EmployeeBean existingBean=allEmployee.get(employeeBean.getId());
			
			if(employeeBean.getDepartmentName()!=null && !employeeBean.getDepartmentName().isBlank())
				existingBean.setDepartmentName(employeeBean.getDepartmentName());
			if(employeeBean.getEmployeeName()!=null && !employeeBean.getEmployeeName().isBlank())
				existingBean.setEmployeeName(employeeBean.getEmployeeName());
			if(employeeBean.getSalary()!=null && !employeeBean.getSalary().equals(0))
				existingBean.setSalary(employeeBean.getSalary());
			
			allEmployee.put(existingBean.getId(), existingBean);

			return existingBean;

		}
		return null;
	}

	@Override
	public List<EmployeeBean> getEmployeesOfDepartment(String department) {

		return allEmployee.entrySet().stream()
				.filter(entry -> entry.getValue().getDepartmentName().equalsIgnoreCase(department))
				.map(e -> e.getValue()).collect(Collectors.toList());
	}

}
