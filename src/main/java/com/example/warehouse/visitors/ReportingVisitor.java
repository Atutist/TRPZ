package com.example.warehouse.visitors;

import com.example.warehouse.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingVisitor implements UserVisitor {

    private static final Logger logger = LoggerFactory.getLogger(ReportingVisitor.class);

    @Override
    public void visitAdmin(User user) {
        logger.info("User: " + user.getUsername() + " is viewing admin page.");
    }

    @Override
    public void visitWarehouseWorker(User user) {
        logger.info("User: " + user.getUsername() + " is managing warehouse operations.");
    }

    @Override
    public void visitWarehouseManager(User user) {
        logger.info("User: " + user.getUsername() + " is working in warehouse system.");
    }

    @Override
    public void visitUserLogout(User user) {
        logger.info("User: " + user.getUsername() + " has ended the session.");
    }
}
