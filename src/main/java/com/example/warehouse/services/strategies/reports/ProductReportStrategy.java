package com.example.warehouse.services.strategies.reports;
import com.example.warehouse.entity.Product;
import com.example.warehouse.repositories.ProductRepository;
import com.example.warehouse.services.strategies.ReportStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductReportStrategy implements ReportStrategy {
    private final ProductRepository productRepository;
    @Override
    public List<Product> generateReport() {

        return productRepository.findAll();
    }
}


