package com.example.tallerfinal.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cashout")

public class Cashout {

    @Id
    private String id;
    private String userId;
    private Double amount;

    // Constructor sin argumentos
    public Cashout() {}

    // Constructor con argumentos
    public Cashout(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
