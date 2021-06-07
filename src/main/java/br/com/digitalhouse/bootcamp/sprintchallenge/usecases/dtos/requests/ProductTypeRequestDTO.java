package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests;

public class ProductTypeRequestDTO {

    private String name;

    public ProductTypeRequestDTO() {
    }

    public ProductTypeRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
