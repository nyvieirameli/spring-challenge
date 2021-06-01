package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.UserGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

    UserGateway userGateway;

    public UserServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }
}
