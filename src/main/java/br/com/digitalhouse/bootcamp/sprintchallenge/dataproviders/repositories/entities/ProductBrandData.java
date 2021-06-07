package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ProductBrand")
public class ProductBrandData implements Comparable<ProductBrandData>{

    @Id
    private UUID id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<ProductData> products;

    public ProductBrandData() {
    }

    public ProductBrandData(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        products = new ArrayList<>();
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

    @JsonIgnore
    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    @Override
    public int compareTo(ProductBrandData o) {
        return this.name.compareTo(o.getName());
    }
}
