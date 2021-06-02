package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.SellerService;

import java.util.List;
import java.util.UUID;

public class SellerServiceImpl implements SellerService {

    UserGateway userGateway;

    public SellerServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserData> getAllSellers() {
        return null;
    }

    @Override
    public Long countSellers() {
        return null;
    }

    @Override
    public void followAnotherSeller(UUID followerId, UUID followedId) {

    }

    @Override
    public void unfollowAnotherSeller(UUID followerId, UUID followedId) {

    }

    @Override
    public Long countFollowers(UUID userId) {
        return null;
    }
}
