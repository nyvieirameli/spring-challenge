package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Product")
public class ProductData implements Comparable<ProductData> {

    @Id
    private UUID id;

    private String name;

    private String color;

    private String notes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProductBrandData brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProductTypeData type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductPostData> posts;

    public ProductData() {
    }

    public ProductData(String name, String color, String notes, ProductBrandData brand, ProductTypeData type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.color = color;
        this.notes = notes;
        this.brand = brand;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ProductBrandData getBrand() {
        return brand;
    }

    public void setBrand(ProductBrandData brand) {
        this.brand = brand;
    }

    public ProductTypeData getType() {
        return type;
    }

    public void setType(ProductTypeData type) {
        this.type = type;
    }

    public List<ProductPostData> getPosts() {
        return posts;
    }

    public void setPosts(List<ProductPostData> posts) {
        this.posts = posts;
    }

    @Override
    public int compareTo(ProductData o) {
        return this.name.compareTo(o.getName());
    }
}
