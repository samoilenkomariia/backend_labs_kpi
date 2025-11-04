package org.example.expensetracker.service;

import org.example.expensetracker.exception.*;
import org.example.expensetracker.model.Category;
import org.example.expensetracker.model.Record;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.CategoryRepository;
import org.example.expensetracker.repository.RecordRepository;
import org.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository  userRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Record getRecordById(UUID recordId, UUID userId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
        if (!record.getUser().getId().equals(userId)) {
            throw new RecordAccessDeniedException("You do not have permission to view this record.");
        }
        return record;
    }

    public Record createRecord(Record record) {
        UUID userId = record.getUser().getId();
        UUID categoryId = record.getCategory().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (category.getUser() != null && !category.getUser().getId().equals(user.getId())) {
            throw new CategoryAccessDeniedException("User does not have access to this category");
        }
        record.setUser(user);
        record.setCategory(category);
        record.setRecordDate(LocalDateTime.now());
        record.setId(null);
        return recordRepository.save(record);
    }

    public void deleteRecordById(UUID recordId, UUID userId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));

        if (!record.getUser().getId().equals(userId)) {
            throw new RecordAccessDeniedException("You do not have permission to delete this record.");
        }
        recordRepository.delete(record);
    }

    public List<Record> getRecordsByFilter(UUID userId, UUID categoryId) {
        if (userId != null && categoryId != null) {
            return recordRepository.findByUserIdAndCategoryId(userId, categoryId);
        } else if (userId != null) {
            return recordRepository.findByUserId(userId);
        } else if (categoryId != null) {
            return recordRepository.findByCategoryId(categoryId);
        } else {
            return recordRepository.findAll();
        }
    }
}