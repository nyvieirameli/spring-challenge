package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.UserData;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.BadRequestException;
import br.com.digitalhouse.bootcamp.sprintchallenge.exceptions.NotFoundException;
import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.dtos.requests.UserRequestDTO;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    UserGateway userGateway;

    public AdminServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserData> getAllUsers(String order) {
        order = (order == null || order.isBlank()) ? "name-asc" : order;

        var users = userGateway.getAllUsers(order);

        if (users == null || users.size() == 0) {
            throw new NotFoundException("List is empty");
        }

        return users;
    }

    @Override
    public UserData getUserById(UUID id) {
        if (id == null) {
            throw new BadRequestException("Body is empty");
        }

        return userGateway.getUser(id);
    }

    @Override
    public Long countUsers() {
        return userGateway.countUsers();
    }

    @Override
    public UserData createUser(UserRequestDTO request) {
        if (request.getName() == null || request.getName().isBlank()
                || request.getCpf() == null || request.getCpf().isBlank()
                || request.getBirthdate() == null
                || request.getType() == null) {

            throw new BadRequestException("Insert all parameters correctly");
        }

        var cleanCpf = request.getCpf().replaceAll("\\D", "");
        if (cleanCpf.length() != 11) {

            throw new BadRequestException("CPF format is wrong");
        }

        if (request.getBirthdate().isAfter(LocalDate.now().minusYears(13))) {

            throw new BadRequestException("You must have at least 13 years old");
        }

        return userGateway.createUser(request);
    }
}
