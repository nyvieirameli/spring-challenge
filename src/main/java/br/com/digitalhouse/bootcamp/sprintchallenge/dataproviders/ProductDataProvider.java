package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.*;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.helpers.UserType;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductPostRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductDataProvider implements ProductGateway {

    ProductBrandRepository brandRepository;
    ProductTypeRepository typeRepository;
    ProductRepository productRepository;
    ProductPostRepository postRepository;
    UserRepository userRepository;

    public ProductDataProvider(ProductBrandRepository brandRepository,
                               ProductTypeRepository typeRepository,
                               ProductRepository productRepository,
                               ProductPostRepository postRepository,
                               UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public <T> List<T> getAll(T obj) {
        List<T> response = null;

        if (obj instanceof ProductData) {

            response = (List<T>) productRepository.findAll();
        }
        else if (obj instanceof ProductBrandData) {

            response = (List<T>) brandRepository.findAll();
        }
        else if (obj instanceof ProductTypeData) {

            response = (List<T>) typeRepository.findAll();
        }
        else if (obj instanceof ProductPostData) {

            response = (List<T>) postRepository.findAll();
        }

        if (response == null) {
            throw new IllegalArgumentException("Obj doesn't have an instance");
        }

        response = response.stream().sorted().collect(Collectors.toList());

        return response;
    }

    @Override
    public ProductData getProductById(UUID id) {
        var product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return product.get();
    }

    @Override
    public ProductData createProduct(ProductRequestDTO request) {
        var brand = brandRepository.findById(request.getProductBrandId());
        if (brand.isEmpty()) {
            throw new BadRequestException("Brand selected not found");
        }

        var type = typeRepository.findById(request.getProductTypeId());
        if (type.isEmpty()) {
            throw new BadRequestException("Type selected not found");
        }

        var user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new BadRequestException("User selected not found");
        }

        var product = new ProductData(request.getName(), request.getColor(), request.getNotes(), user.get(), brand.get(), type.get());

        product = productRepository.save(product);

        return product;
    }

    @Override
    public ProductBrandData createProductBrand(ProductBrandRequestDTO request) {
        var brand = new ProductBrandData(request.getName());

        brand = brandRepository.save(brand);

        return brand;
    }

    @Override
    public ProductTypeData createProductType(ProductTypeRequestDTO request) {
        var type = new ProductTypeData(request.getName());

        type = typeRepository.save(type);

        return type;
    }

    @Override
    public ProductPostData getProductPostById(UUID id) {
        var post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new NotFoundException("Post not found");
        }

        return post.get();
    }

    @Override
    public ProductPostData createNewProductPost(ProductPostData postData, UUID productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new BadRequestException("Product is null");
        }

        postData.setProduct(product.get());
        postData = postRepository.save(postData);

        return postData;
    }

    @Override
    public ProductPostData updateProductPostById(ProductPostRequestDTO request, UUID id) {
        var optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            throw new NotFoundException("Post not found");
        }

        var post = optionalPost.get();
        post.setCategory(request.getCategory() == null ? post.getCategory() : request.getCategory());
        post.setQuantity(request.getQuantity() == null ? post.getQuantity() : request.getQuantity());
        post.setHasPromo(request.getHasPromo() == null ? post.getHasPromo() : request.getHasPromo());
        post.setDiscount(request.getDiscount() == null ? post.getDiscount() : request.getDiscount());

        if (request.getProductId() != null) {
            var product = productRepository.findById(request.getProductId());
            post.setProduct(product.isPresent() ? product.get() : post.getProduct());
        }

        postRepository.save(post);

        return post;
    }

    @Override
    public List<ProductPostData> getProductPostsByUser(UUID userId, String order) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var user = optionalUser.get();

        if (!user.getType().equals(UserType.SELLER.name())) {
            throw new BadRequestException("User is invalid");
        }

        var posts = user.getProducts().stream()
                .flatMap(products -> products.getPosts().stream())
                .collect(Collectors.toList());

        Collections.sort(posts);

        if (!order.contains("asc")) {
            Collections.reverse(posts);
        }

        return posts;
    }

    @Override
    public List<ProductPostData> getProductPostsByFollowedUsersByUserId(UUID userId, String order) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var user = optionalUser.get();

        var followeds = user.getFollowing();

        var response = followeds.stream()
                .flatMap(f -> f.getProducts().stream()
                .flatMap(products -> products.getPosts().stream()))
                .collect(Collectors.toList());

        var weeksAgo = (LocalDateTime.now().minusDays(14));

        response = response.stream().filter(p -> p.getDate().isAfter(ChronoLocalDateTime.from(weeksAgo))).collect(Collectors.toList());

        Collections.sort(response);

        if (!order.contains("asc")) {
            Collections.reverse(response);
        }

        return response;
    }

    @Override
    public Long countPromoProductPostsByUser(UUID userId) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var user = optionalUser.get();

        if (!user.getType().equals(UserType.SELLER.name())) {
            throw new BadRequestException("User is invalid");
        }

        var posts = user.getProducts().stream()
                .flatMap(products -> products.getPosts().stream()
                        .filter(post -> post.getHasPromo()))
                .collect(Collectors.toList());

        return posts.stream().count();
    }
}
