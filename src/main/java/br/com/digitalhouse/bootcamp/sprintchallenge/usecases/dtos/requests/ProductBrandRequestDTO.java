package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests;

public class ProductBrandRequestDTO {

    private String name;

    public ProductBrandRequestDTO() {
    }

    public ProductBrandRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
