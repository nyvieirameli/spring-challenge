package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductBrandRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductPostRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.ProductTypeRepository;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        else if (obj instanceof ProductPostRepository) {

            response = (List<T>) postRepository.findAll();
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

        var product = new ProductData(request.getName(), request.getColor(), request.getNotes(), brand.get(), type.get());

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


}
