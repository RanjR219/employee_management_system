package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employee {

    @Id
    private int employeeId;
    private String name;
    private String aadhar;
    private Integer age;
    private String department;
    private String city;
    private LocalDate dob;


}
