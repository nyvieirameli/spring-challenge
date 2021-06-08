package br.com.digitalhouse.bootcamp.sprintchallenge.configuration.service;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.*;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    private UserRepository userRepository;
    private ProductBrandRepository brandRepository;
    private ProductTypeRepository typeRepository;
    private ProductRepository productRepository;
    private ProductPostRepository postRepository;

    public DBService(UserRepository userRepository,
                     ProductBrandRepository brandRepository,
                     ProductTypeRepository typeRepository,
                     ProductRepository productRepository,
                     ProductPostRepository postRepository) {
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
        this.postRepository = postRepository;
    }

    public void InstantiateH2Database(){
        var buyer1 = new UserData("Nycolas", "12345678911", LocalDate.of(2000, 5, 27), "BUYER");
        var buyer2 = new UserData("Onias", "12345678911", LocalDate.of(2000, 5, 27), "BUYER");
        var seller1 = new UserData("Carolina", "12345678911", LocalDate.of(2000, 5, 27), "SELLER");
        var seller2 = new UserData("Leonardo", "12345678911", LocalDate.of(2000, 5, 27), "SELLER");
        var seller3 = new UserData("Gustavo", "12345678911", LocalDate.of(2000, 5, 27), "SELLER");
        this.userRepository.saveAll(Arrays.asList(buyer1, buyer2, seller1, seller2, seller3));

        var brand1 = new ProductBrandData("Nike");
        var brand2 = new ProductBrandData("Apple");
        this.brandRepository.saveAll(Arrays.asList(brand1, brand2));

        var type1 = new ProductTypeData("Tênis");
        var type2 = new ProductTypeData("Notebook");
        this.typeRepository.saveAll(Arrays.asList(type1, type2));

        var product1 = new ProductData("Air Max", "Branco e vermelho", "Tênis original", seller1, brand1, type1);
        var product2 = new ProductData("Macbook", "Cinza", "1TB de SSD e 16GB de RAM", seller2, brand2, type2);
        this.productRepository.saveAll(Arrays.asList(product1, product2));

        var post1 = new ProductPostData(110, 350.00, 3);
        post1.setProduct(product1);
        var post2 = new ProductPostData(100, 9999.99, 1);
        post2.setProduct(product2);
        this.postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
