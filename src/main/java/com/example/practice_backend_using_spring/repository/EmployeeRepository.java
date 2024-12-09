package com.example.practice_backend_using_spring.repository;

import com.example.practice_backend_using_spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);
}
