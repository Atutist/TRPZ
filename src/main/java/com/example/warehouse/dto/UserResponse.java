package com.example.warehouse.dto;

import com.example.warehouse.entity.User;
import com.example.warehouse.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    int id;
    String name;
    Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.role = user.getRole();
    }
}
