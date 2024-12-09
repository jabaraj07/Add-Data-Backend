package com.example.practice_backend_using_spring.service;

import com.example.practice_backend_using_spring.model.Employee;
import com.example.practice_backend_using_spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    public Employee saveuser(Employee employee) {
//            String hashpass = encoder.encode(employee.getPassword());
//            employee.setPassword(hashpass);
//         return  employeeRepository.save(employee);
//
//        }

    public Employee saveuser(Employee employee){
        String hashpass = encoder.encode(employee.getPassword());
        employee.setPassword(hashpass);
       return employeeRepository.save(employee);
    }

    public boolean add(Employee employee) {
        String hashpass = encoder.encode(employee.getPassword());
        employee.setPassword(hashpass);
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> getuser() {
        return employeeRepository.findAll();
    }
    public Employee check(String email,String password){
        Employee exist = employeeRepository.findByEmail(email);
        if(exist != null && encoder.matches(password,exist.getPassword())){
            return exist;
        }
        return null;
    }
}
