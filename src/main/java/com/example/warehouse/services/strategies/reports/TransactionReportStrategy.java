package com.example.warehouse.services.strategies.reports;

import com.example.warehouse.entity.Transaction;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.services.strategies.ReportStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionReportStrategy implements ReportStrategy {
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> generateReport() {
        return transactionRepository.findAll();
    }
}

