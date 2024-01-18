package com.example.warehouse.entity;

import com.example.warehouse.visitors.UserVisitor;
import com.example.warehouse.visitors.VisitableUser;
import com.example.warehouse.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "users")
public class User implements VisitableUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Override
    public void accept(UserVisitor visitor) {
        switch (this.role) {
            case ADMIN:
                visitor.visitAdmin(this);
                break;
            case WORKER:
                visitor.visitWarehouseWorker(this);
                break;
            case BOSS:
                visitor.visitWarehouseManager(this);
                break;
            default:
                throw new RuntimeException("Unknown role: " + this.role);
        }
    }
}
