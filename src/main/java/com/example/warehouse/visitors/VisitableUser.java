package com.example.warehouse.visitors;

public interface VisitableUser {
    void accept(UserVisitor visitor);
}
