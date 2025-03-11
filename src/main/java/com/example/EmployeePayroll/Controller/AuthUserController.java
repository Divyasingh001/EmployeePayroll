package com.example.EmployeePayroll.Controller;
import com.example.EmployeePayroll.DTO.AuthUserDTO;
import com.example.EmployeePayroll.DTO.LoginDTO;
import com.example.EmployeePayroll.DTO.ResponseDTO;
import com.example.EmployeePayroll.Service.AuthenticationService;
import com.example.EmployeePayroll.model.AuthUser;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody AuthUserDTO userDTO) throws Exception {
        AuthUser user = authenticationService.register(userDTO);
        ResponseDTO responseUserDTO = new ResponseDTO("User details is submitted!", user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        String result = authenticationService.login(loginDTO);
        ResponseDTO responseUserDTO = new ResponseDTO("Login successfully!!", result);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
}