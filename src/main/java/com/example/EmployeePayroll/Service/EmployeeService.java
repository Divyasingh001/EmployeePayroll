package com.example.EmployeePayroll.Service;

import com.example.EmployeePayroll.Interface.IemployeeService;
import com.example.EmployeePayroll.Repository.employeeRepo;
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IemployeeService {

    @Autowired
    private employeeRepo employeeRepository;

    @Override
    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeModel createUser(EmployeeModel user) {
        return employeeRepository.save(user);
    }

    @Override
    public Optional<EmployeeModel> updateUser(Long id, EmployeeModel userDetails) {
        return employeeRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return employeeRepository.save(user);
        });
    }

    @Override
    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
