package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.enums.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "User")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String cpf;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private LocalDate birthdate;

    @Column(name = "type", columnDefinition = "int")
    private UserType type;

    public UserData(String name, String cpf, LocalDate birthdate, UserType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.birthdate = birthdate;
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
