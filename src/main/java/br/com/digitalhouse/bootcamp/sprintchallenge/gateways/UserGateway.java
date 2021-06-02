package br.com.digitalhouse.bootcamp.sprintchallenge.gateways;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.enums.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserGateway {

    List<UserData> getAllUsers();
    List<UserData> getUsersByType(UserType type);
    UserData getUser(UUID id);
    Long countUsers();
    Long countUsersByType(UserType type);
    UserData createUser(UserRequestDTO user);
    UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType);
    void deleteUser(UUID id);
    void followSeller(UUID followerId, UUID followedId);
    void unfollowSeller(UUID followerId, UUID followedId);
    void countFollowers(UUID userId);
}
