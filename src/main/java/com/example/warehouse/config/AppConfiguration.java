package com.example.warehouse.config;

import com.example.warehouse.factories.TransactionFactory;
import com.example.warehouse.factories.transaction.ProductionTransactionFactory;
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
