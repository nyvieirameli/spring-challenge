package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.UserRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDataProvider implements UserGateway {

    UserRepository userRepository;

    public UserDataProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserData> getAllUsers() {
        var users = userRepository.findAll();

        Collections.sort(users);

        return users;
    }

    @Override
    public List<UserData> getUsersByType(UserType type) {
        var users = userRepository.findAll().stream()
                .filter(u -> u.getType().equals(type.name()))
                .collect(Collectors.toList());

        Collections.sort(users);

        return users;
    }

    @Override
    public UserData getUser(UUID id) {
        var userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return userData.get();
        }

        throw new NotFoundException("User not found");
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public Long countUsersByType(UserType type) {
        return userRepository.findAll().stream()
                .filter(u -> u.getType().equals(type.name()))
                .count();
    }

    @Override
    public UserData createUser(UserRequestDTO request) {
        var userData = new UserData(request.getName(), request.getCpf(), request.getBirthdate(), request.getType().name());
        userData = userRepository.save(userData);

        return userData;
    }

    @Override
    public UserData updateUser(UUID id, String name, LocalDate birthdate, UserType userType) {
        var optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var userData = optionalUser.get();
        userData.setName(name);
        userData.setBirthdate(birthdate);
        userData.setType(userType.name());

        userData = userRepository.save(userData);

        return userData;
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public void followSeller(UUID followerId, UUID followedId) {
        var users = userRepository.findAllById(new ArrayList<>(Arrays.asList(followerId, followedId)));

        if (users.size() != 2) {
            throw new NotFoundException("Users not found");
        }

        var follower = users.stream().filter(u -> u.getId().equals(followerId)).findFirst().get();
        var followed = users.stream().filter(u -> u.getId().equals(followedId)).findFirst().get();

        if (!followed.getType().equals(UserType.SELLER.name())) {
            throw new BadRequestException("User can't be followed");
        }

        follower.setFollowing(followed);
        followed.setFollower(follower);

        userRepository.saveAll(new ArrayList<>(Arrays.asList(follower, followed)));
    }

    @Override
    public void unfollowSeller(UUID followerId, UUID followedId) {
        var users = userRepository.findAllById(new ArrayList<>(Arrays.asList(followerId, followedId)));

        if (users.size() != 2) {
            throw new NotFoundException("Users not found");
        }

        var follower = users.stream().filter(u -> u.getId().equals(followerId)).findFirst().get();
        var followed = users.stream().filter(u -> u.getId().equals(followedId)).findFirst().get();

        follower.getFollowing().remove(followed);
        followed.getFollowers().remove(follower);

        userRepository.saveAll(new ArrayList<>(Arrays.asList(follower, followed)));
    }

    @Override
    public List<UserData> getFollowers(UUID userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty() || !user.get().getType().equals(UserType.SELLER.name())) {
            throw new BadRequestException("User is invalid");
        }

        return user.get().getFollowers();
    }

    @Override
    public Long countFollowers(UUID userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty() || !user.get().getType().equals(UserType.SELLER.name())) {
            throw new BadRequestException("User is invalid");
        }

        return user.get().getFollowers().stream().count();
    }

    @Override
    public List<UserData> getFollowing(UUID userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new BadRequestException("User is invalid");
        }

        return user.get().getFollowing();
    }

    @Override
    public Long countFollowing(UUID userId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new BadRequestException("User is invalid");
        }

        return user.get().getFollowing().stream().count();
    }
}