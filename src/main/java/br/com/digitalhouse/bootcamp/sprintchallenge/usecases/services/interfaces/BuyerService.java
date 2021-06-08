package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;

import java.util.List;
import java.util.UUID;

public interface BuyerService {

    List<UserData> getAllBuyers(String order);
    Long countBuyers();
    void followSeller(UUID buyerId, UUID sellerId);
    void unfollowSeller(UUID buyerId, UUID sellerId);
    List<UserData> getFollowing(UUID userId, String order);
    Long countFollowing(UUID userId);

}
