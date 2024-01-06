package com.example.warehouse.services;

import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.entity.User;
import com.example.warehouse.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthorizationService {
    private UserRepository userDAO;

    public Optional<UserResponse> checkUser(String username, String password){
        Optional<User> user = userDAO.findByUsernameIgnoreCase(username);
        if (user.isPresent() && password.equals(user.get().getPassword())){
            return user.map(UserResponse::new);
        }
        return Optional.empty();
    }
    public boolean isUserExist(String username){
        Optional<User> user = userDAO.findByUsernameIgnoreCase(username);
        return user.isPresent();
    }
}
