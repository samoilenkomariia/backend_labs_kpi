package org.example.expensetracker.controller;

import org.example.expensetracker.model.Category;
import org.example.expensetracker.service.CategoryService; // Changed import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestParam(name = "user_id", required = false) UUID userId) {
        Category savedCategory = categoryService.createCategory(category, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/custom/byUser/{userId}")
    public ResponseEntity<List<Category>> getCustomCategoryByUserId(@PathVariable UUID userId) {
        var customCategories = categoryService.getCustomCategoriesByUser(userId);
        return ResponseEntity.ok(customCategories);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Category>> getCategoriesForUser(@PathVariable UUID userId) {
        List<Category> categories = categoryService.getCategoriesForUser(userId);
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId, @RequestParam(name = "user_id") UUID userId) {
        categoryService.deleteCategory(categoryId, userId);
        return ResponseEntity.noContent().build();
    }
}