package com.example.warehouse.controllers;

import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.services.RecipeService;
import com.example.warehouse.services.WareHouseService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WareHouseController {

    private final RecipeService recipeService;
    private final WareHouseService wareHouseService;



    @GetMapping("/worker/issueProduct")
    public String issueProduct() {
        return "/worker/issueProduct";
    }

    @GetMapping("/worker/receiveMaterial")
    public String receiveMaterial() {
        return "/worker/receiveMaterial";
    }

    @PostMapping("/worker/receiveMaterial")
    public String receiveMaterial(@RequestParam Integer materialId, @RequestParam Double amount, HttpSession session, Model model) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        wareHouseService.receiveMaterial(materialId, amount, user);
        model.addAttribute("message", "Material received");
        return "/worker/receiveMaterial";
    }

    @PostMapping("/worker/issueProduct")
    public String issueProduct(@RequestParam Integer productId, @RequestParam Double amount, HttpSession session, Model model) {
        UserResponse user = (UserResponse) session.getAttribute("user");
        wareHouseService.issueProduct(productId, amount, user);
        model.addAttribute("message", "Product issued");
        return "/worker/issueProduct";
    }

}
