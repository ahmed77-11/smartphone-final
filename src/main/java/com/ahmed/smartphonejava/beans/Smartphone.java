package com.ahmed.smartphonejava.beans;

import java.util.Date;

public class Smartphone {
    private int id;
    private String brand;
    private String model;
    private Double price;
    private Date created_at;
    private Category category;

    public Smartphone(String brand, String model, Double price, Category category) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.category = category;
    }

    public Smartphone(int id, String brand, String model, Double price, Category category) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.category = category;
    }
    public Smartphone() {
        super();
    }
    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", created_at=" + created_at +
                ", category=" + category +
                '}';
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
