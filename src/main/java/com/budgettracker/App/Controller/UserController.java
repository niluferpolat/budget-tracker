package com.budgettracker.App.Controller;

import com.budgettracker.App.Dto.LoginDto;
import com.budgettracker.App.Entity.CustomUserDetails;
import com.budgettracker.App.Entity.User;
import com.budgettracker.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public User authenticateUser(@RequestBody LoginDto loginDto) {
      CustomUserDetails customUserDetails=userService.loginUser(loginDto);
      return customUserDetails.user;
    }

}
