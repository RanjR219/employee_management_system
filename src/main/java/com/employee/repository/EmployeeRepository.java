package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeByAadhar(String aadhar);
}
