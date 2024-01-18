package com.example.warehouse.process;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TransactionProcess {
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcess.class);

    protected Double amount;
    protected String username;
    protected Material material;
    protected Product product;
    protected TransactionFactory transactionFactory;
    protected TransactionService transactionService;
    public TransactionProcess(Double amount, String username, Material material, Product product,
                              TransactionFactory transactionFactory, TransactionService transactionService) {
        this.amount = amount;
        this.username = username;
        this.material = material;
        this.product = product;
        this.transactionFactory = transactionFactory;
        this.transactionService = transactionService;
    }
    public final void executeTransaction() {
        try {
            startTransaction();
            Thread.sleep(5000);
            executeSpecificOperation();
            endTransaction();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Transaction process interrupted", e);
        }
    }
    private void startTransaction() {
        logger.info("Starting Transaction");
    }
    protected abstract void executeSpecificOperation();
    private void endTransaction() {
        logger.info("Ending Transaction");
    }
}
