package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ProductPost")
public class ProductPostData implements Comparable<ProductPostData>{

    @Id
    private UUID id;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    private Integer category;

    private Double price;

    private Integer quantity;

    private Boolean hasPromo;

    private Double discount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProductData product;

    public ProductPostData() {
    }

    public ProductPostData(LocalDate date, Integer category, Double price, Integer quantity) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.hasPromo = false;
        this.discount = 0.0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }

    @Override
    public int compareTo(ProductPostData o) {
        return this.date.compareTo(o.getDate());
    }
}
