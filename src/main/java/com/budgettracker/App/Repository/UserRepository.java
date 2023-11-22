package com.budgettracker.App.Repository;

import com.budgettracker.App.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndEmail(String username, String email);
    User findByUsername(String username);
}
