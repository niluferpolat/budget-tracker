package com.budgettracker.App.Service;

import com.budgettracker.App.Entity.User;
import com.budgettracker.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        Optional<User> userOptional=userRepository.findByUsernameAndEmail(user.getUsername(),user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalArgumentException("This user already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
