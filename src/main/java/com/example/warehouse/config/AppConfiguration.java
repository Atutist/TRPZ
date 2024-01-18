package com.example.warehouse.config;

import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.factories.transaction.ProductionTransactionFactory;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.repositories.ProductRepository;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.services.strategies.ReportStrategy;
import com.example.warehouse.services.strategies.reports.MaterialReportStrategy;
import com.example.warehouse.services.strategies.reports.ProductReportStrategy;
import com.example.warehouse.services.strategies.reports.TransactionReportStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AppConfiguration {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    public TransactionFactory transactionFactory() {
        return new ProductionTransactionFactory();
    }

}
