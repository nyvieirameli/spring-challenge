package br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories;

import br.com.digitalhouse.bootcamp.sprintchallenge.dataproviders.repositories.entities.ProductPostData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPostData, UUID> {
}
