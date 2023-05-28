package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.exception.ApplicationException;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Integer addEmployee(EmployeeDTO employeeDTO) throws ApplicationException, EmployeeAlreadyExistsException {
        Employee existingEmployee = employeeRepository.findEmployeeByAadhar(employeeDTO.getAadhar());
        if( existingEmployee != null) {
            throw new EmployeeAlreadyExistsException("AADHAR_ALREADY_EXISTS");
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setDob(employeeDTO.getDob());
        employee.setAge(employeeDTO.getAge());
        employee.setAadhar(employeeDTO.getAadhar());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setCity(employeeDTO.getCity());
        employeeRepository.save(employee);
        return employeeDTO.getEmployeeId();
    }

    @Override
    public EmployeeDTO getEmployee(String aadhar) throws ApplicationException {
        Employee employee = employeeRepository.findEmployeeByAadhar(aadhar);
        if(employee == null || aadhar == "") {
            throw new ApplicationException("EMPLOYEE_DOES_NOT_EXIST");
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAadhar(employee.getAadhar());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setCity(employee.getCity());
        return employeeDTO;
    }

    @Override
    public void updateEmployee(String aadhar, EmployeeDTO employeeDTO) throws ApplicationException {
        Employee employee = employeeRepository.findEmployeeByAadhar(aadhar);
        if(employee == null || aadhar == "") {
            throw new ApplicationException("AADHAR_DOES_NOT_EXIST");
        }
        employee.setDepartment(employeeDTO.getDepartment());
    }
    @Override
    public List<EmployeeDTO> retrieveAllEmployees() throws ApplicationException {
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setDob(employee.getDob());
            employeeDTO.setName(employee.getName());
            employeeDTO.setAadhar(employee.getAadhar());
            employeeDTO.setAge(employee.getAge());
            employeeDTO.setDepartment(employee.getDepartment());
            employeeDTO.setCity(employee.getCity());
            employeeDTOS.add(employeeDTO);
        });
        if (employeeDTOS.isEmpty())
            throw new ApplicationException("USERS_NOT_FOUND");
        return employeeDTOS;
    }
}
