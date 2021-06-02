package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.UserRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.enums.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserDataProvider implements UserGateway {

    UserRepository repository;

    public UserDataProvider(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserData> getAllUsers() {
        var users = repository.findAll();

        Collections.sort(users);

        return users;
    }

    @Override
    public List<UserData> getUsersByType(UserType type) {
        var users = repository.findAll().stream()
                .filter(u -> u.getType().equals(type.name()))
                .collect(Collectors.toList());

        Collections.sort(users);

        return users;
    }

    @Override
    public UserData getUser(UUID id) {
        var userData = repository.findById(id);

        if (userData.isPresent()) {
            return userData.get();
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
                .filter(u -> u.getType().equals(type.name()))
                .count();
    }

    @Override
    public UserData createUser(UserRequestDTO request) {
        var userData = new UserData(request.getName(), request.getCpf(), request.getBirthdate(), request.getType().name());
        userData = repository.save(userData);

        return userData;
    }

    @Override
    public UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var userData = optionalUser.get();
        userData.setName(name);
        userData.setBirthdate(birthdate);
        userData.setType(userType.name());

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

    @Override
    public void followSeller(UUID followerId, UUID followedId) {

    }

    @Override
    public void unfollowSeller(UUID followerId, UUID followedId) {

    }

    @Override
    public void countFollowers(UUID userId) {

    }
}