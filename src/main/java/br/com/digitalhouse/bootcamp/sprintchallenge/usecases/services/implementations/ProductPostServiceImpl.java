package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductPostService;
import org.springframework.stereotype.Service;

@Service
public class ProductPostServiceImpl implements ProductPostService {

    private ProductGateway gateway;

    public ProductPostServiceImpl(ProductGateway gateway) {
        this.gateway = gateway;
    }
}
