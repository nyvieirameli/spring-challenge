package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductPostRequestDTO {

    private Integer category;

    private Double price;

    private Integer quantity;

    private Boolean hasPromo;

    private Double discount;

    private UUID productId;

    public ProductPostRequestDTO() {
    }

    public ProductPostRequestDTO(Integer category, Double price, Integer quantity, Boolean hasPromo, Double discount, UUID productId) {
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.productId = productId;
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

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
