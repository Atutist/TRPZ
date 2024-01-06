package com.example.warehouse.controllers;


import com.example.warehouse.entity.Material;
import com.example.warehouse.entity.Transaction;
import com.example.warehouse.repositories.MaterialRepository;
import com.example.warehouse.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class ManagerController {
    private final TransactionRepository transactionRepository;
    private final MaterialRepository materialRepository;


    @GetMapping("/boss/reports")
    private String viewReports(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "/boss/reports";
    }

    //===Create connections!!!====
    @GetMapping("/boss/manageInventory")
    public String manageInventory(Model model) {
        List<Material> materials = materialRepository.findAll(Sort.by("id").ascending());
        model.addAttribute("materials", materials);
        return "/boss/manageInventory";
    }

    @PostMapping("/boss/updateMinimumStock")
    public String updateMinimumStock(@RequestParam("materialId") Integer materialId,
                                     @RequestParam("minimumStock") Double minimumStock) {
        Optional<Material> materialOpt = materialRepository.findById(materialId);
        if (materialOpt.isPresent()) {
            Material material = materialOpt.get();
            material.setMinimumStock(minimumStock);
            materialRepository.save(material);
        }
        return "redirect:/boss/manageInventory";
    }


}
