package br.com.digitalhouse.bootcamp.sprintchallenge.gateways;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
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

}
