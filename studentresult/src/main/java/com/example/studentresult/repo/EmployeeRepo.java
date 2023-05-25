package com.example.studentresult.repo;

import com.example.studentresult.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

    List<Employee> findByRegistrationNumber(String registrationNumber);
}
