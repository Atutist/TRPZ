package com.example.warehouse.services;

import com.example.warehouse.entity.*;
import com.example.warehouse.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;
    private final RecipeListRepository recipeListRepository;
    private final TransactionRepository transactionRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeByProductId(Integer productId) {
        return recipeRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Recipe for product with ID " + productId + " not found"));
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Recipe with name " + name + " not found"));
    }

    @Transactional
    public void createRecipe(String recipeName, List<Integer> materialIds, List<Double> materialAmounts) {
        Product product = new Product();
        product.setName(recipeName);
        product.setAmount(0.0);
        Product savedProduct = productRepository.save(product);


        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setProduct(savedProduct);
        Recipe savedRecipe = recipeRepository.save(recipe);

        List<RecipeList> recipeLists = new ArrayList<>();
        for (int i = 0; i < materialIds.size(); i++) {
            Material material = materialRepository.findById(materialIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Material not found"));
            RecipeList recipeList = new RecipeList();
            recipeList.setMaterial(material);
            recipeList.setMaterialAmount(materialAmounts.get(i));
            recipeList.setRecipe(savedRecipe); // Важливо: зв'язування зі збереженим рецептом
            recipeLists.add(recipeList);
        }

        recipeListRepository.saveAll(recipeLists);
    }



    @Transactional
    public void useRecipe(Integer recipeId, Double amount, String username) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (RecipeList recipeList : recipe.getRecipeLists()) {
            Material material = recipeList.getMaterial();
            if (material.getAmount() < recipeList.getMaterialAmount() * amount) {
                throw new RuntimeException("Insufficient material: " + material.getName());
            }
        }

        for (RecipeList recipeList : recipe.getRecipeLists()) {
            Material material = recipeList.getMaterial();
            double newAmount = material.getAmount() - recipeList.getMaterialAmount() * amount;
            material.setAmount(newAmount);
            materialRepository.save(material);
        }


        Transaction transaction = new Transaction.Builder()
                .setType("RECEIVE")
                .setProduct(recipe.getProduct())
                .setAmount(amount)
                .setDateTime(LocalDateTime.now())
                .setUsername(username)
                .build();
        transactionRepository.save(transaction);

        Product product = recipe.getProduct();
        product.setAmount(product.getAmount() + amount);
        productRepository.save(product);
    }


}
