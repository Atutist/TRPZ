package com.example.warehouse.Visitor;

public interface VisitableUser {
    void accept(UserVisitor visitor);
}
