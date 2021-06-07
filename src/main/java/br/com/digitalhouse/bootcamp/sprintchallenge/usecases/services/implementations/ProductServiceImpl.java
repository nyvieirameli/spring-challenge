package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductGateway gateway;

    public ProductServiceImpl(ProductGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<ProductData> getAllProducts() {
        var products = gateway.getAll(new ProductData());

        if (products == null || products.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return products;
    }

    @Override
    public ProductData getProductById(UUID id) {
        var product = gateway.getProductById(id);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        return product;
    }

    @Override
    public ProductData createProduct(ProductRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()
                || request.getColor() == null || request.getColor().isBlank()
                || request.getProductBrandId() == null
                || request.getProductTypeId() == null) {

            throw new BadRequestException("Insert all parameter correctly");
        }

        return gateway.createProduct(request);
    }

    @Override
    public List<ProductBrandData> getAllProductBrands() {
        var productBrands = gateway.getAll(new ProductBrandData());

        if (productBrands == null || productBrands.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return productBrands;
    }

    @Override
    public ProductBrandData createProductBrand(ProductBrandRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()) {

            throw new BadRequestException("Insert all parameter correctly");
        }

        return gateway.createProductBrand(request);
    }

    @Override
    public List<ProductTypeData> getAllProductTypes() {
        var productTypes = gateway.getAll(new ProductTypeData());

        if (productTypes == null || productTypes.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return productTypes;
    }

    @Override
    public ProductTypeData createProductType(ProductTypeRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()) {

            throw new BadRequestException("Insert all parameter correctly");
        }

        return gateway.createProductType(request);
    }
}
