package br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.implementations;

import br.com.digitalhouse.bootcamp.sprintchallenge.gateways.ProductGateway;
import br.com.digitalhouse.bootcamp.sprintchallenge.usecases.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductGateway gateway;

    public ProductServiceImpl(ProductGateway gateway) {
        this.gateway = gateway;
    }
}
