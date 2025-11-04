package org.example.expensetracker.controller;

import org.example.expensetracker.model.Record;
import org.example.expensetracker.service.RecordService; // Changed import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Record> getRecordById(
            @PathVariable UUID recordId,
            @RequestParam(name = "user_id") UUID userId
    ) {
        Record record = recordService.getRecordById(recordId, userId);
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        Record savedRecord = recordService.createRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteRecordById(
                                                  @PathVariable UUID recordId,
                                                  @RequestParam(name = "user_id") UUID userId
    ) {
        recordService.deleteRecordById(recordId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Record>> getRecordsByFilter(
                                                           @RequestParam(name="user_id", required = false) UUID userId,
                                                           @RequestParam(name="category_id", required=false) UUID categoryId
    ) {
        List<Record> filteredRecords = recordService.getRecordsByFilter(userId, categoryId);
        return ResponseEntity.ok(filteredRecords);
    }
}