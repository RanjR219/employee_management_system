package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.exception.ApplicationException;
import com.employee.exception.EmployeeAlreadyExistsException;

import java.util.List;

public interface EmployeeService {

    Integer addEmployee(EmployeeDTO employeeDTO) throws ApplicationException, EmployeeAlreadyExistsException;

    EmployeeDTO getEmployee(String aadhar) throws ApplicationException;

    void updateEmployee( String aadhar, EmployeeDTO employee) throws ApplicationException;

    List<EmployeeDTO> retrieveAllEmployees() throws ApplicationException;
}
