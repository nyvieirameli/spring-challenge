package br.com.digitalhouse.bootcamp.sprintchallenge.gateways;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserGateway {

    List<UserData> getAllUsers(String order);
    List<UserData> getUsersByType(UserType type, String order);
    UserData getUser(UUID id);
    Long countUsers();
    Long countUsersByType(UserType type);
    UserData createUser(UserRequestDTO user);
    UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType);
    void deleteUser(UUID id);
    void followSeller(UUID followerId, UUID followedId);
    void unfollowSeller(UUID followerId, UUID followedId);
    List<UserData> getFollowers(UUID userId, String order);
    Long countFollowers(UUID userId);
    List<UserData> getFollowing(UUID userId, String order);
    Long countFollowing(UUID userId);
}
