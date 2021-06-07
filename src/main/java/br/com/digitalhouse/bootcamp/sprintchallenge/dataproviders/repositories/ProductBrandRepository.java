package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductBrandData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrandData, UUID> {
}
