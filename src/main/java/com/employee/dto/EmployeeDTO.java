package com.employee.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    @Id
    private int employeeId;
    @NotNull(message = "Name should not be null")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should be alphabets only")
    private String name;

    @NotNull(message = "Aadhar should not be null")
    @Pattern(regexp = "^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$", message="Aadhar number is not valid")
    private String aadhar;
    private Integer age;
    private String department;
    private String city;
    @PastOrPresent(message = "Date should not be of future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

}
