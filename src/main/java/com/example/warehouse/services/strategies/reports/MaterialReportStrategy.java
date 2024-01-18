package com.example.warehouse.services.strategies.reports;
import com.example.warehouse.entity.Material;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.services.strategies.ReportStrategy;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialReportStrategy implements ReportStrategy {
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> generateReport() {

        return materialRepository.findAll(Sort.by("id").ascending());
    }
}


