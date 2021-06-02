package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    List<UserData> getAllUsers();
    UserData getUserById(UUID id);
    Long countUsers();
    UserData createUser(UserRequestDTO request);

}
