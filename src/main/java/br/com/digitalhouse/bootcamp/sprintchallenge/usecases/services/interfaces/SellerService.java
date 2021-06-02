package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;

import java.util.List;
import java.util.UUID;

public interface SellerService {

    List<UserData> getAllSellers();
    Long countSellers();
    void followAnotherSeller(UUID followerId, UUID followedId);
    void unfollowAnotherSeller(UUID followerId, UUID followedId);
    Long countFollowers(UUID userId);
}
