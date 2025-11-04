package org.example.expensetracker.repository;

import org.example.expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByUserIdIsNullOrUserId(UUID userId);
    List<Category> findByUserId(UUID userId);
}
