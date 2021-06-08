package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductPostRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ProductPostService {

    List<ProductPostData> getAllProductPosts();
    ProductPostData getProductPostById(UUID id);
    ProductPostData createNewProductPost(ProductPostRequestDTO request);
    ProductPostData updateProductPostById(ProductPostRequestDTO request, UUID id);
    List<ProductPostData> getProductPostsByUser(UUID userId);
    List<ProductPostData> getProductPostsByFollowedUsersByUserId(UUID userId, String order);
    List<ProductPostData> getPromoProductPostsByUser(UUID userId);
    Long countPromoProductPostsByUser(UUID userId);
}
