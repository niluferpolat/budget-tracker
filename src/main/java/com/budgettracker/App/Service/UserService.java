package com.budgettracker.App.Service;

import com.budgettracker.App.Dto.LoginDto;
import com.budgettracker.App.Entity.User;
import com.budgettracker.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    public UserService(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public User registerUser(User user){
        Optional<User> userOptional=userRepository.findByUsernameAndEmail(user.getUsername(),user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalArgumentException("This user already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(LoginDto loginDto) {
        try {
            Authentication auth = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "basarili";
        } catch (AuthenticationException e) {
            return "error";
        }
    }

}
