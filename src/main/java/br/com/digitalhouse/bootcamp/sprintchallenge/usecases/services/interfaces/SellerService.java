package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;

import java.util.List;
import java.util.UUID;

public interface SellerService {

    List<UserData> getAllSellers(String order);
    Long countSellers();
    void followAnotherSeller(UUID followerId, UUID followedId);
    void unfollowAnotherSeller(UUID followerId, UUID followedId);
    Long countFollowers(UUID userId);
    List<UserData> getFollowers(UUID userId, String order);
    List<UserData> getFollowing(UUID userId, String order);
    Long countFollowing(UUID userId);
}
