package com.example.warehouse.process;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.factories.transaction.ReceiveMaterialTransactionFactory;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.services.TransactionService;

public class ReceiveMaterialProcess extends TransactionProcess {

    public ReceiveMaterialProcess(Double amount, String username, Material material,
                                  TransactionFactory transactionFactory,
                                  TransactionService transactionService) {
        super(amount, username, material, null, transactionFactory, transactionService);
    }

    @Override
    protected void executeSpecificOperation() {
        Transaction transaction = transactionFactory.createTransaction("RECEIVE", amount, username, material, null);
        transactionService.saveTransaction(transaction);
    }
}


