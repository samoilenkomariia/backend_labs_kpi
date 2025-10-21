package org.example.expensetracker.controller;

import org.example.expensetracker.model.Record;
import org.example.expensetracker.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/record")
public class RecordController {
    private final RecordService service;

    @Autowired
    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Record> getRecordById(@PathVariable UUID recordId) {
        Record record = service.getRecordById(recordId);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        return service.createRecord(record);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Record> deleteRecordById(@PathVariable UUID recordId) {
        if (service.deleteRecord(recordId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getRecordByFilter(
            @RequestParam(name="user_id", required=false) UUID userId,
            @RequestParam(name="category_id", required=false) UUID categoryId
    ) {
        if (userId == null && categoryId == null) {
            return ResponseEntity.badRequest()
                    .body("Error: at least one query parameter is required");
        }
        List<Record> filteredRecords = service.findRecordByFilter(userId, categoryId);
        return ResponseEntity.ok(filteredRecords);
    }
}
