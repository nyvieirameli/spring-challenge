package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductBrandRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductPostRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductTypeRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import org.springframework.stereotype.Service;

@Service
public class ProductDataProvider implements ProductGateway {

    ProductBrandRepository brandRepository;
    ProductTypeRepository typeRepository;
    ProductRepository productRepository;
    ProductPostRepository postRepository;

    public ProductDataProvider(ProductBrandRepository brandRepository, ProductTypeRepository typeRepository, ProductRepository productRepository, ProductPostRepository postRepository) {
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
        this.postRepository = postRepository;
    }


}
