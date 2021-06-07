package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses.ResponseDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/sellers")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserData>>> findAllSellers() {
        var response = new ResponseDTO<List<UserData>>();

        try {
            var users = sellerService.getAllSellers();

            response.setData(users);
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

    @GetMapping("/count")
    public ResponseEntity<ResponseDTO<Long>> countSellers() {
        var response = new ResponseDTO<Long>();

        try {
            var users = sellerService.countSellers();

            response.setData(users);
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

    @PostMapping("/{userId}/follow/{followedId}")
    public ResponseEntity<ResponseDTO> followSeller(@PathVariable UUID userId, @PathVariable UUID followedId) {
        var response = new ResponseDTO<>();

        try {
            sellerService.followAnotherSeller(userId, followedId);

            response.setData(null);
            response.setHttpStatus(HttpStatus.NO_CONTENT);
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

    @PostMapping("/{userId}/unfollow/{followedId}")
    public ResponseEntity<ResponseDTO> unfollowSeller(@PathVariable UUID userId, @PathVariable UUID followedId) {
        var response = new ResponseDTO<>();

        try {
            sellerService.followAnotherSeller(userId, followedId);

            response.setData(null);
            response.setHttpStatus(HttpStatus.NO_CONTENT);
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

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<ResponseDTO<List<UserData>>> getFollowers(@PathVariable UUID userId) {
        var response = new ResponseDTO<List<UserData>>();

        try {
            var followers = sellerService.getFollowers(userId);

            response.setData(followers);
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

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<ResponseDTO<Long>> countFollowers(@PathVariable UUID userId) {
        var response = new ResponseDTO<Long>();

        try {
            var followersCount = sellerService.countFollowers(userId);

            response.setData(followersCount);
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

    @GetMapping("/{userId}/following")
    public ResponseEntity<ResponseDTO<List<UserData>>> getFollowing(@PathVariable UUID userId) {
        var response = new ResponseDTO<List<UserData>>();

        try {
            var following = sellerService.getFollowing(userId);

            response.setData(following);
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

    @GetMapping("/{userId}/following/count")
    public ResponseEntity<ResponseDTO<Long>> getFollowingCount(@PathVariable UUID userId) {
        var response = new ResponseDTO<Long>();

        try {
            var following = sellerService.countFollowing(userId);

            response.setData(following);
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
