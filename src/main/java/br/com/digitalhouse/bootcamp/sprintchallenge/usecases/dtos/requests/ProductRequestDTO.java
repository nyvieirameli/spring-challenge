package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests;

import java.util.UUID;

public class ProductRequestDTO {

    private String name;

    private String color;

    private String notes;

    private UUID productBrandId;

    private UUID productTypeId;

    private UUID userId;

    public ProductRequestDTO(){
    }

    public ProductRequestDTO(String name, String color, String notes, UUID productBrandId, UUID productTypeId, UUID userId) {
        this.name = name;
        this.color = color;
        this.notes = notes;
        this.productBrandId = productBrandId;
        this.productTypeId = productTypeId;
        this.userId = userId;
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

    public UUID getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(UUID productBrandId) {
        this.productBrandId = productBrandId;
    }

    public UUID getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(UUID productTypeId) {
        this.productTypeId = productTypeId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
