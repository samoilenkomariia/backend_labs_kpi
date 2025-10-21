package org.example.expensetracker.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class Record {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public Record() {}

    public Record(UUID id, UUID userId, UUID categoryId, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Record record)) return false;
        return Objects.equals(getId(), record.getId()) && Objects.equals(getUserId(), record.getUserId()) && Objects.equals(getCategoryId(), record.getCategoryId()) && Objects.equals(getAmount(), record.getAmount()) && Objects.equals(getCreatedAt(), record.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCategoryId(), getAmount(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Record{" +
                "amount=" + amount +
                ", createdAt=" + createdAt +
                ", id=" + id +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
