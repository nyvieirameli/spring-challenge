package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.SellerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SellerServiceImpl implements SellerService {

    UserGateway userGateway;

    public SellerServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserData> getAllSellers() {
        return userGateway.getUsersByType(UserType.SELLER);
    }

    @Override
    public Long countSellers() {
        return userGateway.countUsersByType(UserType.SELLER);
    }

    @Override
    public void followAnotherSeller(UUID followerId, UUID followedId) {
        var following = userGateway.getFollowing(followerId);

        var exists = following.stream().filter(f -> f.getId().equals(followedId)).count();
        if (exists > 0) {
            throw new BadRequestException("You already following this seller");
        }

        userGateway.followSeller(followerId, followedId);
    }

    @Override
    public void unfollowAnotherSeller(UUID followerId, UUID followedId) {
        var following = userGateway.getFollowing(followerId);

        var exists = following.stream().filter(f -> f.getId().equals(followedId)).count();
        if (exists == 0) {
            throw new BadRequestException("You aren't following this seller");
        }

        userGateway.unfollowSeller(followerId, followedId);
    }

    @Override
    public Long countFollowers(UUID userId) {
        return userGateway.countFollowers(userId);
    }

    @Override
    public List<UserData> getFollowers(UUID userId) {
        return userGateway.getFollowers(userId);
    }

    @Override
    public List<UserData> getFollowing(UUID userId) {
        return userGateway.getFollowing(userId);
    }

    @Override
    public Long countFollowing(UUID userId) {
        return userGateway.countFollowing(userId);
    }
}
