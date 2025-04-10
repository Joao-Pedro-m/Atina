package com.atina.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private int userId;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductState state;
    private LocalDateTime createdAt;

    // Enum para os estados
    public enum ProductState {
        DISPONIVEL("disponível"),
        RESERVADO("reservado"),
        VENDIDO("vendido");

        private final String dbValue;

        ProductState(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }

        public static ProductState fromString(String text) {
            for (ProductState ps : ProductState.values()) {
                if (ps.dbValue.equalsIgnoreCase(text)) {
                    return ps;
                }
            }
            throw new IllegalArgumentException("Estado inválido: " + text);
        }
    }

    // Construtores
    public Product() {}

    public Product(int userId, String name, String description, BigDecimal price) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = ProductState.DISPONIVEL;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public ProductState getState() { return state; }
    public void setState(ProductState state) { this.state = state; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
