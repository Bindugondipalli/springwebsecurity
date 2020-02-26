package com.cts.swmd.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.swmd.dao.EmployeeRepository;
import com.cts.swmd.exception.EmployeeException;
import com.cts.swmd.model.Department;
import com.cts.swmd.model.Employee;
@Service
 public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee add(Employee emp) throws EmployeeException {
		if(emp!=null) {
			if(empRepo.existsById(emp.getEmpId())) {
				throw new EmployeeException("There exisits an employee with givenID");
			}else if(empRepo.existsByMobileNumber(emp.getMobileNumber())) {
				throw new EmployeeException("There exists an employee with givenmobileNumber");
			}else if(empRepo.existsByEmail(emp.getEmail())) {
				throw new EmployeeException("There exists an employee with givenEmail");
			}else {
				emp=empRepo.save(emp);
			}
		}
		return emp;
	}

	@Override
	public Employee save(Employee emp) throws EmployeeException {
		if(emp!=null) {
			Employee oldEmp=empRepo.findById(emp.getEmpId()).orElse(null);
			if(oldEmp==null) {
				throw new EmployeeException("There does not exixts an employee with the given id");
				
			}else if(!oldEmp.getMobileNumber().equals(emp.getMobileNumber())&& empRepo.existsByMobileNumber(emp.getMobileNumber())){
				throw new EmployeeException("There does not exixts an employee with given mobileNumber");
			}else if(!oldEmp.getEmail().equals(emp.getEmail())&& empRepo.existsByEmail(emp.getEmail())){
				throw new EmployeeException("There does not exixts an employee with given email");
			}
			else {
				emp=empRepo.save(emp);
			}
		}
		
		return null;
	}

	@Override
	public void deleteById(Long empId) {
		empRepo.deleteById(empId);
		
	}

	@Override
	public Employee findById(Long empId) {
		
		return empRepo.findById(empId).orElse(null);
	}
	
    public Employee findByMobileNumber(Long mobileNumber) {
		
		return empRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public Employee findByEmail(String email) {
		
		return empRepo.findByEmail(email);
	}

	@Override
	public List<Employee> findAll() {
		
		return empRepo.findAll();
	}

	@Override
	public List<Employee> findAllByDept(Department dept) {
	
		return empRepo.findAllByDept(dept);
	}

	@Override
	public List<Employee> findAllByJoinDate(LocalDate joinDate) {
		
		return empRepo.findAllByJoinDate(joinDate);
	}

	@Override
	public List<Employee> findAllByBasicRange(double lb, double ub) {
		
		return empRepo.findAllByBasicRange(lb, ub);
	}

}
