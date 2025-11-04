package org.example.expensetracker.service;

import org.example.expensetracker.exception.CategoryAccessDeniedException;
import org.example.expensetracker.exception.CategoryNotFoundException;
import org.example.expensetracker.exception.UserNotFoundException;
import org.example.expensetracker.model.Category;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.CategoryRepository;
import org.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Category> getCategoriesForUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return categoryRepository.findByUserIdIsNullOrUserId(userId);
    }

    public List<Category> getCustomCategoriesByUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return categoryRepository.findByUserId(userId);
    }

    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    public Category createCategory(Category category, UUID userId) {
        User user = null;
        if (userId != null) {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
        }

        category.setUser(user);
        category.setId(null);
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID categoryId, UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        if (category.getUser() == null) {
            throw new CategoryAccessDeniedException("Cannot delete a global category.");
        }
        if (!category.getUser().getId().equals(userId)) {
            throw new CategoryAccessDeniedException("You do not have permission to delete this category.");
        }
        categoryRepository.delete(category);
    }
}
