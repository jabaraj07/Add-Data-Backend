package com.example.practice_backend_using_spring.service;

import com.example.practice_backend_using_spring.model.Employee;
import com.example.practice_backend_using_spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void deleteemployee(Long userid) {
        employeeRepository.deleteById(userid);
    }

    public void setuser(String name, String email, String password,Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null){
            employee.setName(name);
            employee.setEmail(email);
            employee.setPassword(encoder.encode(password));
            employeeRepository.save(employee);
        }
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee checkuser(Long userid) {
        return employeeRepository.findById(userid).orElse(null);
    }

//    public Employee finduser(Long userId) {
//        return employeeRepository.findById(userId).orElse(null);
//    }
}
