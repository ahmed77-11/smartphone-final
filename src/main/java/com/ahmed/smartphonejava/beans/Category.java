package com.ahmed.smartphonejava.beans;

import javafx.scene.control.TextField;

import java.util.Date;
import java.util.Objects;

public class Category {
    private int id;
    private String nom;
    private String description;
    private Date create_at;

    public Category() {
        super();
    }


    public Category(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    public Category(int id, String nom, String description,Date create_at) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.create_at=create_at;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", create_at=" + create_at +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
