package br.com.digitalhouse.bootcamp.sprintchallenge.controllers;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.responses.ResponseDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserData>>> findAllUsers(@RequestParam(value = "order", required = false) String order) {
        var response = new ResponseDTO<List<UserData>>();

        try {
            var users = adminService.getAllUsers(order);

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
            var user = adminService.getUserById(userId);

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

    @GetMapping("/count")
    public ResponseEntity<ResponseDTO<Long>> countUsers() {
        var response = new ResponseDTO<Long>();

        try {
            var users = adminService.countUsers();

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

    @PostMapping
    @ExceptionHandler
    public ResponseEntity<ResponseDTO<UserData>> createNewUser(@RequestBody UserRequestDTO userRequest) {
        var response = new ResponseDTO<UserData>();

        try {
            var user = adminService.createUser(userRequest);

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

    /**
     * TODO: use reflection
     * @param response generic return with ResponseDTO
     * @param method reflection for call methods insise try/catch
     * @param <T> type from ResponseDTO (response)
     * @return new ResponseEntity with responseDTO as body
     */
    private <T> ResponseEntity validateService(ResponseDTO<T> response, Method method) {
        try {
            var data = (T)method.invoke(adminService);

            response.setData(data);
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
