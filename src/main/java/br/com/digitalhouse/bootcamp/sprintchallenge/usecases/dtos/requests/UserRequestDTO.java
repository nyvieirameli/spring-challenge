package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;

import java.time.LocalDate;

public class UserRequestDTO {

    private String name;
    private String cpf;
    private LocalDate birthdate;
    private UserType type;

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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
