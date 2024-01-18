package com.example.warehouse.process;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.factories.transaction.IssueProductTransactionFactory;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.services.TransactionService;

public class IssueProductProcess extends TransactionProcess {
    public IssueProductProcess(Double amount, String username, Product product,
                               TransactionFactory transactionFactory,
                               TransactionService transactionService) {
        super(amount, username, null, product, transactionFactory, transactionService);
    }

    @Override
    protected void executeSpecificOperation() {
        Transaction transaction = transactionFactory.createTransaction("ISSUE", amount, username, null, product);
        transactionService.saveTransaction(transaction); // Use transactionService to save the transaction
    }
}

