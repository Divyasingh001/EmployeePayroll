package com.example.EmployeePayroll.Controller;

import com.example.EmployeePayroll.Service.EmployeeService;
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public List<EmployeeModel> getAllUsers() {
        return employeeService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public EmployeeModel createUser(@RequestBody EmployeeModel employee) {
        return employeeService.createUser(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @RequestBody EmployeeModel userDetails) {
        return employeeService.updateUser(id, userDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (employeeService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
