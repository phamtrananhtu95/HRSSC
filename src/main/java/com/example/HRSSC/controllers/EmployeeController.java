package com.example.HRSSC.controllers;

import com.example.HRSSC.models.Employee;
import com.example.HRSSC.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Thien on 6/11/2018.
 */
@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }
}
