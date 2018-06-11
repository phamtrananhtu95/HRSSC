package com.example.HRSSC.services.api.impl;

import com.example.HRSSC.models.Employee;
import com.example.HRSSC.services.api.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thien on 6/11/2018.
 */
@Service
public class EmployeeServiceImp implements EmployeeService{
    @Override
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("User1");
        emp1.setCompany("Company1");
        emp1.setEmail("aaa@gmail.com");
        emp1.setExperience("No exp");
        emp1.setSkill("Java");

        emp1.setMatched(1);

        employeeList.add(emp1);

        return employeeList;
    }
}
