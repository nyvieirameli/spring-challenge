package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses.ResponseDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserData>>> findAllUsers() {
        var response = new ResponseDTO<List<UserData>>();

        try {
            var users = userService.getAllUsers();

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

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDTO<UserData>> findUserById(@PathVariable UUID userId) {
        var response = new ResponseDTO<UserData>();

        try {
            var user = userService.getUserById(userId);

            response.setData(user);
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
    @ExceptionHandler
    public ResponseEntity<ResponseDTO<UserData>> createNewUser(@RequestBody UserRequestDTO userRequest) {
        var response = new ResponseDTO<UserData>();

        try {
            var user = userService.createUser(userRequest);

            response.setData(user);
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
