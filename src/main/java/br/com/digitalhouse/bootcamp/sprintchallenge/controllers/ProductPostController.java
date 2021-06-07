package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/posts")
public class ProductPostController {

    private ProductPostService postService;

    public ProductPostController(ProductPostService postService) {
        this.postService = postService;
    }
}
