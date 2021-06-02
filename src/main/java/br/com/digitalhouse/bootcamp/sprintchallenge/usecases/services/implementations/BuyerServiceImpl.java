package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.BuyerService;

import java.util.List;
import java.util.UUID;

public class BuyerServiceImpl implements BuyerService {

    UserGateway userGateway;

    public BuyerServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserData> getAllBuyers() {
        return null;
    }

    @Override
    public Long countBuyers() {
        return null;
    }

    @Override
    public void followSeller(UUID buyerId, UUID sellerId) {

    }

    @Override
    public void unfollowSeller(UUID buyerId, UUID sellerId) {

    }
}
