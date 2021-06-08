package br.com.digitalhouse.bootcamp.sprintchallenge.gateways;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductPostRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ProductGateway {

    <T> List<T> getAll(T obj);
    ProductData getProductById(UUID id);
    ProductData createProduct(ProductRequestDTO request);
    ProductBrandData createProductBrand(ProductBrandRequestDTO request);
    ProductTypeData createProductType(ProductTypeRequestDTO request);
    ProductPostData getProductPostById(UUID id);
    ProductPostData createNewProductPost(ProductPostData postData, UUID productId);
    ProductPostData updateProductPostById(ProductPostRequestDTO request, UUID id);
    List<ProductPostData> getProductPostsByUser(UUID userId);
    List<ProductPostData> getProductPostsByFollowedUsersByUserId(UUID userId, String order);
    Long countPromoProductPostsByUser(UUID userId);
}
