package com.example.warehouse.services;

import com.example.warehouse.entity.User;
import com.example.warehouse.enums.Role;
import com.example.warehouse.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById (Integer id){ return  userRepository.findById(id); }
    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void createUser(String username, String password, Role role){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(role);
        createUser(user);
    }

    public Optional<User> authenticateUser(String username, String password){
        return userRepository.findByUsernameIgnoreCase(username)
                .filter(user -> user.getPassword().equals(password));
    }
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
