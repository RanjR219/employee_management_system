package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.exception.ApplicationException;
import com.employee.exception.EmployeeAlreadyExistsException;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employees")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO employee) throws ApplicationException, EmployeeAlreadyExistsException {
        Integer id = employeeService.addEmployee(employee);
        String successMessage = "INSERT_SUCCESS " + id;
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);

    }

    @GetMapping(value = "/employees/{aadhar}")
    public  ResponseEntity<EmployeeDTO> retrieveOneEmployee(@PathVariable String aadhar) throws ApplicationException {
        EmployeeDTO employee =  employeeService.getEmployee(aadhar);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{aadhar}")
    public ResponseEntity<String> updateEmployee(@PathVariable String aadhar, @RequestBody EmployeeDTO employee) throws ApplicationException{
        employeeService.updateEmployee(aadhar, employee);
        String successMessage = "UPDATE_SUCCESS ";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }


    @GetMapping(value = "/employees")
    public ResponseEntity <List<EmployeeDTO>> retrieveAllEmployees() throws  ApplicationException{
         List<EmployeeDTO> employee = employeeService.retrieveAllEmployees();
            return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
