package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.ProductPostRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses.ResponseDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products/posts")
public class ProductPostController {

    private ProductPostService postService;

    public ProductPostController(ProductPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ProductPostData>>> findAllProductPosts() {
        var response = new ResponseDTO<List<ProductPostData>>();

        try {
            var posts = postService.getAllProductPosts();

            response.setData(posts);
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

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDTO<ProductPostData>> findProductPostById(@PathVariable UUID postId) {
        var response = new ResponseDTO<ProductPostData>();

        try {
            var post = postService.getProductPostById(postId);

            response.setData(post);
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
    public ResponseEntity<ResponseDTO<ProductPostData>> createNewProductPost(@RequestBody ProductPostRequestDTO request) {
        var response = new ResponseDTO<ProductPostData>();

        try {
            request.setHasPromo(false);
            
            var post = postService.createNewProductPost(request);

            response.setData(post);
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

    @PostMapping("/new-promo")
    public ResponseEntity<ResponseDTO<ProductPostData>> createNewPromoProductPost(@RequestBody ProductPostRequestDTO request) {
        var response = new ResponseDTO<ProductPostData>();

        try {
            request.setHasPromo(true);

            var post = postService.createNewProductPost(request);

            response.setData(post);
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


    @PutMapping("/{postId}")
    public ResponseEntity<ResponseDTO<ProductPostData>> editProductPost(@PathVariable UUID postId, @RequestBody ProductPostRequestDTO request) {
        var response = new ResponseDTO<ProductPostData>();

        try {
            var post = postService.updateProductPostById(request, postId);

            response.setData(post);
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

    @GetMapping("/{userId}/list")
    public ResponseEntity<ResponseDTO<List<ProductPostData>>> findProductPostsByUser(@PathVariable UUID userId) {
        var response = new ResponseDTO<List<ProductPostData>>();

        try {
            var posts = postService.getProductPostsByUser(userId);

            response.setData(posts);
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

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponseDTO<List<ProductPostData>>> findProductPostsByFollowedUsersByUserId(
            @PathVariable UUID userId,
            @RequestParam(value = "order", required = false) String order) {
        var response = new ResponseDTO<List<ProductPostData>>();

        try {
            var posts = postService.getProductPostsByFollowedUsersByUserId(userId, order);

            response.setData(posts);
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

    @GetMapping("/{userId}/list/promo")
    public ResponseEntity<ResponseDTO<List<ProductPostData>>> findPromoProductPostsByUser(@PathVariable UUID userId) {
        var response = new ResponseDTO<List<ProductPostData>>();

        try {
            var posts = postService.getPromoProductPostsByUser(userId);

            response.setData(posts);
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

    @GetMapping("/{userId}/promo/count")
    public ResponseEntity<ResponseDTO<Long>> countPromoProductPostsByUser(@PathVariable UUID userId) {
        var response = new ResponseDTO<Long>();

        try {
            var count = postService.countPromoProductPostsByUser(userId);

            response.setData(count);
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
