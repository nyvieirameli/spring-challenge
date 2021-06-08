package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "User")
public class UserData implements Comparable<UserData> {

    @Id
    private UUID id;

    private String name;

    private String cpf;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private LocalDate birthdate;

    private String type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserFollower",
            joinColumns = @JoinColumn(name = "followerId"),
            inverseJoinColumns = @JoinColumn(name = "followingId")
    )
    private List<UserData> following;

    @ManyToMany(mappedBy = "following")
    private List<UserData> followers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ProductData> products;

    public UserData() {
    }

    public UserData(String name, String cpf, LocalDate birthdate, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public List<UserData> getFollowing() {
        return following;
    }

    public void setFollowing(UserData following) {
        if (this.following == null) {
            this.following = new ArrayList<>();
        }

        this.following.add(following);
    }

    public void setFollowers(List<UserData> followers) {
        this.followers = followers;
    }

    @JsonIgnore
    public List<UserData> getFollowers() {
        return followers;
    }

    public void setFollower(UserData follower) {
        if (this.followers == null) {
            this.followers = new ArrayList<>();
        }

        this.followers.add(follower);
    }

    @JsonIgnore
    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    @Override
    public int compareTo(UserData o) {
        return this.name.compareTo(o.getName());
    }
}
