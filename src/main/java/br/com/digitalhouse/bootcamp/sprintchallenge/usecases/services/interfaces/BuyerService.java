package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.util.List;
import java.util.UUID;

public interface BuyerService {

    List<UserData> getAllBuyers();
    Long countBuyers();
    void followSeller(UUID buyerId, UUID sellerId);
    void unfollowSeller(UUID buyerId, UUID sellerId);

}
