package com.example.warehouse.controllers;

import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.entity.*;
import com.example.warehouse.services.RecipeService;
import com.example.warehouse.services.MaterialService;
import com.example.warehouse.services.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final MaterialService materialService;
    private final ProductService productService;


    @GetMapping("/boss/createNewRecipe")
    public String createRecipeForm(Model model) {
        List<Material> materials = materialService.getAllMaterials();
        model.addAttribute("materials", materials);
        return "/boss/createNewRecipe";
    }


    @PostMapping("/boss/createNewRecipe")
    public String createRecipe(@RequestParam String name,
                               @RequestParam List<Integer> materialIds,
                               @RequestParam List<Double> materialAmounts,
                               Model model) {
        try {
            recipeService.createRecipe(name, materialIds, materialAmounts);
            model.addAttribute("message", "Рецепт успішно створено.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Помилка при створенні рецепту: " + e.getMessage());
        }
        return "redirect:/boss/createNewRecipe";
    }
    @GetMapping("/worker/production")
    public String showUseRecipePage(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        model.addAttribute("products", productService.getAllProducts());
        return "/worker/production";
    }


    @PostMapping("/worker/useRecipe")
    public String useRecipe(@RequestParam("productId") Integer productId,
                            @RequestParam("amount") Double amount,
                            Model model, HttpSession session) {
        try {
            UserResponse currentUser = (UserResponse) session.getAttribute("user");
            if (currentUser == null) {
                throw new RuntimeException("User not authenticated");
            }
            Recipe recipe = recipeService.getRecipeByProductId(productId);
            if (recipe == null) {
                throw new RuntimeException("Recipe for selected product not found");
            }
            recipeService.useRecipe(recipe.getId(), amount, currentUser.getName());
            model.addAttribute("message", "Продукція успішно вироблена за вибраним продуктом");
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Помилка при виробництві продукції: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Виникла непередбачувана помилка: " + e.getMessage());
        }
        return "/worker/production";
    }
}
