package com.bankingSystem.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long accNo;
    private Double amount;
    private String type;
    private LocalDateTime timestamp;

    public Transaction() {}

    public Transaction(Long id, Long accNo, Double amount, String type, LocalDateTime timestamp) {
        this.id = id;
        this.accNo = accNo;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccNo() {
        return accNo;
    }

    public void setAccNo(Long accNo) {
        this.accNo = accNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
