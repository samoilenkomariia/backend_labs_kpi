package org.example.expensetracker.service;

import org.example.expensetracker.model.Record;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final Map<UUID, Record> recordStorage = new ConcurrentHashMap<>();

    public Record createRecord(Record record) {
        UUID id = UUID.randomUUID();
        record.setId(id);
        record.setCreatedAt(LocalDateTime.now());
        recordStorage.put(id, record);
        return record;
    }

    public Record getRecordById(UUID recordId) {
        return recordStorage.get(recordId);
    }

    public List<Record> getAllRecords() {
        return new ArrayList<>(recordStorage.values());
    }

    public boolean deleteRecord(UUID recordId) {
        return recordStorage.remove(recordId) != null;
    }

    public List<Record> findRecordByFilter(UUID userId, UUID categoryId) {
        return recordStorage.values().stream()
                .filter(r -> userId == null || r.getUserId().equals(userId))
                .filter(r -> categoryId == null || r.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }
}
