package com.example.studentresult.service;

import com.example.studentresult.dto.EmployeeDTO;
import com.example.studentresult.entity.Employee;
import com.example.studentresult.repo.EmployeeRepo;
import com.example.studentresult.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDTO employeeDTO){
        if (employeeRepo.existsById(employeeDTO.getRegistrationNumber())){
            return VarList.RSP_DUPLICATED;
        }else {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(EmployeeDTO employeeDTO){
        if (employeeRepo.existsById(employeeDTO.getRegistrationNumber())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList,new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }
    public interface ICityService {

        List<Employee> findById(String RegistrationNumber);
    }

    public EmployeeDTO searchEmployee(String RegistrationNumber){
        if (employeeRepo.existsById(String.valueOf(RegistrationNumber))){
            Employee employee =employeeRepo.findById(String.valueOf(RegistrationNumber)).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);
        }else {
            return null;
        }
    }
    public String deleteEmployee(String RegistrationNumber){
        if (employeeRepo.existsById(String.valueOf(RegistrationNumber))){
            employeeRepo.deleteById(String.valueOf(RegistrationNumber));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
