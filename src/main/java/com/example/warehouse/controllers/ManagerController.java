package com.example.warehouse.controllers;


import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.entity.Material;
import com.example.warehouse.enums.Role;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.repositories.ProductRepository;
import com.example.warehouse.repositories.TransactionRepository;
import com.example.warehouse.services.strategies.ReportStrategy;
import com.example.warehouse.services.strategies.reports.MaterialReportStrategy;
import com.example.warehouse.services.strategies.reports.ProductReportStrategy;
import com.example.warehouse.services.strategies.reports.TransactionReportStrategy;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ManagerController {
    private final MaterialRepository materialRepository;
    @Autowired
    private MaterialReportStrategy materialReportStrategy;
    @Autowired
    private ProductReportStrategy productReportStrategy;
    @Autowired
    private TransactionReportStrategy transactionReportStrategy;

    @GetMapping("/boss/reports")
    private String viewReports(Model model,
                               @RequestParam(value = "reportType", required = false) String reportType) {
        if (reportType == null) {
            reportType = "transaction";
        }
        ReportStrategy reportStrategy;
        switch (reportType) {
            case "material":
                reportStrategy = materialReportStrategy;
                break;
            case "product":
                reportStrategy = productReportStrategy;
                break;
            case "transaction":
            default:
                reportStrategy = transactionReportStrategy;
                break;
        }

        model.addAttribute("report", reportStrategy.generateReport());
        model.addAttribute("reportType", reportType); // Додано
        return "/boss/reports";
    }

    @GetMapping("/boss/manageInventory")
    public String manageInventory(Model model, HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.BOSS))){
            return "redirect:/accessDenied";
        }
        List<Material> materials = materialRepository.findAll(Sort.by("id").ascending());
        model.addAttribute("materials", materials);
        return "/boss/manageInventory";
    }

    @PostMapping("/boss/updateMinimumStock")
    public String updateMinimumStock(@RequestParam("materialId") Integer materialId,
                                     @RequestParam("minimumStock") Double minimumStock,
                                     HttpSession session) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.BOSS))){
            return "redirect:/accessDenied";
        }
        Optional<Material> materialOpt = materialRepository.findById(materialId);
        if (materialOpt.isPresent()) {
            Material material = materialOpt.get();
            material.setMinimumStock(minimumStock);
            materialRepository.save(material);
        }
        return "redirect:/boss/manageInventory";
    }


}
