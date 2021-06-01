package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.UserRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.enums.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDataProvider implements UserGateway {

    UserRepository repository;

    public UserDataProvider(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserData> getAllUsers() {
        return repository.findAll()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<UserData> getUsersByType(UserType type) {
        return repository.findAll().stream()
                .filter(u -> u.getType() == type)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public UserData getUser(UUID id) {
        var userData = repository.getById(id);

        if (userData != null) {
            return userData;
        }

        throw new NotFoundException("User not found");
    }

    @Override
    public Long countUsers() {
        return repository.count();
    }

    @Override
    public Long countUsersByType(UserType type) {
        return repository.findAll().stream()
                .filter(u -> u.getType() == type)
                .count();
    }

    @Override
    public UserData createUser(UserRequestDTO request) {
        var userData = new UserData(request.getName(), request.getCpf(), request.getBirthdate(), request.getType());
        userData = repository.save(userData);

        return userData;
    }

    @Override
    public UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType) {
        var userData = repository.getById(id);

        if (userData == null) {
            throw new NotFoundException("User not found");
        }

        userData.setName(name);
        userData.setBirthdate(birthdate);
        userData.setType(userType);

        userData = repository.save(userData);

        return userData;
    }

    @Override
    public void deleteUser(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        repository.deleteById(id);
    }
}