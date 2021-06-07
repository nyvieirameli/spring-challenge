package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ProductType")
public class ProductTypeData {

    @Id
    private UUID id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<ProductData> products;

    public ProductTypeData() {
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
}
