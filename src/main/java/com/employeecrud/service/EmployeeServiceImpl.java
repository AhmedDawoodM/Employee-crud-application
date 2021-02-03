package com.employeecrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeecrud.dao.EmployeeRepository;
import com.employeecrud.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		
		Employee employee = null;
		
		if(result.isPresent())
			employee=result.get();
		else
			throw new RuntimeException("Did not find employee id - " + id);
		
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee emplyee) {
		employeeRepository.save(emplyee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}

}
