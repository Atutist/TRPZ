package com.example.warehouse.services;

import com.example.warehouse.entity.Transaction;
import com.example.warehouse.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
