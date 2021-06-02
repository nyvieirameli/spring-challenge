package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserData> getAllUsers();
    public UserData getUserById(UUID id);
    public UserData createUser(UserRequestDTO request);

}
