package com.atina.model;

public class ProductImage {
    private int id;
    private String name;
    private String image;
    private int productId;

    // Construtores
    public ProductImage() {}

    public ProductImage(String name, String image, int productId) {
        this.name = name;
        this.image = image;
        this.productId = productId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
}
