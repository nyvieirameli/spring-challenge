package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductPostRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductPostServiceImpl implements ProductPostService {

    private ProductGateway gateway;

    public ProductPostServiceImpl(ProductGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<ProductPostData> getAllProductPosts() {
        var posts = gateway.getAll(new ProductPostData());

        if (posts == null || posts.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return posts;
    }

    @Override
    public ProductPostData getProductPostById(UUID id) {
        var post = gateway.getProductPostById(id);

        if (post == null) {
            throw new NotFoundException("Post not found");
        }

        return post;
    }

    @Override
    public ProductPostData createNewProductPost(ProductPostRequestDTO request) {
        if (request.getCategory() < 0 || request.getPrice() < 0 || request.getQuantity() < 0) {
            throw new BadRequestException("Insert all values bigger than 0");
        }

        ProductPostData postData;
        if (request.getHasPromo()) {

            if (request.getDiscount() < 0) {
                throw new BadRequestException("Insert all values bigger than 0");
            }

            postData = new ProductPostData(request.getCategory(), request.getPrice(), request.getQuantity(), request.getDiscount());
        }
        else {
            postData = new ProductPostData(request.getCategory(), request.getPrice(), request.getQuantity());
        }

        return gateway.createNewProductPost(postData, request.getProductId());
    }

    @Override
    public ProductPostData updateProductPostById(ProductPostRequestDTO request, UUID id) {
        if (request == null) {
            throw new BadRequestException("Body can't be null");
        }

        return gateway.updateProductPostById(request, id);
    }

    @Override
    public List<ProductPostData> getProductPostsByUser(UUID userId) {
        var posts = gateway.getProductPostsByUser(userId);

        if (posts == null || posts.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return posts;
    }

    @Override
    public List<ProductPostData> getProductPostsByFollowedUsersByUserId(UUID userId) {
        var posts = gateway.getProductPostsByFollowedUsersByUserId(userId);

        if (posts == null || posts.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return posts;
    }

    @Override
    public List<ProductPostData> getPromoProductPostsByUser(UUID userId) {
        var posts = gateway.getProductPostsByUser(userId);

        if (posts == null || posts.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        posts = posts.stream().filter(p -> p.getHasPromo()).collect(Collectors.toList());

        if (posts == null || posts.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return posts;
    }

    @Override
    public Long countPromoProductPostsByUser(UUID userId) {
        return gateway.countPromoProductPostsByUser(userId);
    }
}
