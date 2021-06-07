package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductData;
import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductTypeData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductBrandRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductTypeRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses.ResponseDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ProductData>>> findAllProduct() {
        var response = new ResponseDTO<List<ProductData>>();

        try {
            var products = productService.getAllProducts();

            response.setData(products);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO<ProductData>> findProductById(@PathVariable UUID productId) {
        var response = new ResponseDTO<ProductData>();

        try {
            var product = productService.getProductById(productId);

            response.setData(product);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ProductData>> createNewProduct(@RequestBody ProductRequestDTO request) {
        var response = new ResponseDTO<ProductData>();

        try {
            var product = productService.createProduct(request);

            response.setData(product);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @GetMapping("/product-brands")
    public ResponseEntity<ResponseDTO<List<ProductBrandData>>> findProductBrands() {
        var response = new ResponseDTO<List<ProductBrandData>>();

        try {
            var brands = productService.getAllProductBrands();

            response.setData(brands);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @PostMapping("/product-brands")
    public ResponseEntity<ResponseDTO<ProductBrandData>> createNewProductBrand(@RequestBody ProductBrandRequestDTO request) {
        var response = new ResponseDTO<ProductBrandData>();

        try {
            var brand = productService.createProductBrand(request);

            response.setData(brand);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @GetMapping("/product-types")
    public ResponseEntity<ResponseDTO<List<ProductTypeData>>> findProductTypes() {
        var response = new ResponseDTO<List<ProductTypeData>>();

        try {
            var types = productService.getAllProductTypes();

            response.setData(types);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }

    @PostMapping("/product-types")
    public ResponseEntity<ResponseDTO<ProductTypeData>> createNewProductType(@RequestBody ProductTypeRequestDTO request) {
        var response = new ResponseDTO<ProductTypeData>();

        try {
            var type = productService.createProductType(request);

            response.setData(type);
            response.setHttpStatus(HttpStatus.OK);
        } catch (NotFoundException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {

            response.setError(ex.getMessage());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            response.setError(ex.toString());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, response.getHttpStatus());
    }
}
