package br.com.digitalhouse.bootcamp.sprintchallenge.gateways;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.enums.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserGateway {

    public List<UserData> getAllUsers();
    public List<UserData> getUsersByType(UserType type);
    public UserData getUser(UUID id);
    public Long countUsers();
    public Long countUsersByType(UserType type);
    public UserData createUser(UserRequestDTO user);
    public UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType);
    public void deleteUser(UUID id);

}
