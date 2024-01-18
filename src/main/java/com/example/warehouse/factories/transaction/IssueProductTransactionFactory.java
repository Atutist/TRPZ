package com.example.warehouse.factories.transaction;

import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.factories.TransactionFactory;

import java.time.LocalDateTime;

public class IssueProductTransactionFactory  implements TransactionFactory {
    @Override
    public Transaction createTransaction(String type,
                                         Double amount,
                                         String username,
                                         Material material,
                                         Product product) {
        return new Transaction.Builder()
                .setType(type)
                .setProduct(product)
                .setAmount(amount)
                .setDateTime(LocalDateTime.now())
                .setUsername(username)
                .build();
    }
}


