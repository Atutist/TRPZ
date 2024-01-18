package com.example.warehouse.visitors;

import com.example.warehouse.entity.User;

public interface UserVisitor {
    void visitAdmin(User user);
    void visitWarehouseWorker(User user);
    void visitWarehouseManager(User user);
    void visitUserLogout(User user);
}
