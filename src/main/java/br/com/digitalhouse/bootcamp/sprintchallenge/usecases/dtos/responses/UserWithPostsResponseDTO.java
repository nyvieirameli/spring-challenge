package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserWithPostsResponseDTO {

    private UUID id;

    private String name;

    private String cpf;

    private LocalDate birthdate;

    private String type;

    private List<ProductPostData> posts;

    public UserWithPostsResponseDTO(UserData user, List<ProductPostData> posts) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.birthdate = user.getBirthdate();
        this.type = user.getType();
        this.posts = posts;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductPostData> getPosts() {
        return posts;
    }

    public void setPosts(List<ProductPostData> posts) {
        this.posts = posts;
    }
}
