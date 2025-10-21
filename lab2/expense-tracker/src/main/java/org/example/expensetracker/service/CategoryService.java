package org.example.expensetracker.service;

import org.example.expensetracker.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CategoryService {
    private final Map<UUID, Category> categoryStorage = new ConcurrentHashMap<>();

    public Category createCategory(Category category) {
        UUID uuid = UUID.randomUUID();
        category.setId(uuid);
        categoryStorage.put(uuid, category);
        return category;
    }

    public Category getCategoryById(UUID categoryId) {
        return categoryStorage.get(categoryId);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryStorage.values());
    }

    public boolean deleteCategory(UUID categoryId) {
        return categoryStorage.remove(categoryId) != null;
    }
}
