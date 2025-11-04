package org.example.expensetracker.repository;

import org.example.expensetracker.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<Record, UUID> {
    List<Record> findByUserId(UUID userId);
    List<Record> findByCategoryId(UUID categoryId);
    List<Record> findByUserIdAndCategoryId(UUID userId, UUID categoryId);
}
