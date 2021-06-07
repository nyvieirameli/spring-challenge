package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductData> getAllProducts();
    ProductData getProductById(UUID id);
    ProductData createProduct(ProductRequestDTO request);
    List<ProductBrandData> getAllProductBrands();
    ProductBrandData createProductBrand(ProductBrandRequestDTO request);
    List<ProductTypeData> getAllProductTypes();
    ProductTypeData createProductType(ProductTypeRequestDTO request);

}
