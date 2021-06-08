package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.BuyerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuyerServiceImpl implements BuyerService {

    UserGateway userGateway;

    public BuyerServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserData> getAllBuyers(String order) {
        order = (order == null || order.isBlank()) ? "name-asc" : order;

        var buyers = userGateway.getUsersByType(UserType.BUYER, order);

        if (buyers == null || buyers.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return buyers;
    }

    @Override
    public Long countBuyers() {
        return userGateway.countUsersByType(UserType.BUYER);
    }

    @Override
    public void followSeller(UUID buyerId, UUID sellerId) {
        var following = userGateway.getFollowing(buyerId, "name-asc");

        var exists = following.stream().filter(f -> f.getId().equals(sellerId)).count();
        if (exists > 0) {
            throw new BadRequestException("You already following this seller");
        }

        userGateway.followSeller(buyerId, sellerId);
    }

    @Override
    public void unfollowSeller(UUID buyerId, UUID sellerId) {
        var following = userGateway.getFollowing(buyerId, "name-asc");

        var exists = following.stream().filter(f -> f.getId().equals(sellerId)).count();
        if (exists == 0) {
            throw new BadRequestException("You aren't following this seller");
        }

        userGateway.unfollowSeller(buyerId, sellerId);
    }

    @Override
    public List<UserData> getFollowing(UUID userId, String order) {
        order = (order == null || order.isBlank()) ? "name-asc" : order;

        var following = userGateway.getFollowing(userId, order);

        if (following == null || following.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return following;
    }

    @Override
    public Long countFollowing(UUID userId) {
        return userGateway.countFollowing(userId);
    }
}
