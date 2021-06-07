package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
