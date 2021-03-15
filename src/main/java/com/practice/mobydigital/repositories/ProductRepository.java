package com.practice.mobydigital.repositories;

import com.practice.mobydigital.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    Optional<Product> getById(String id);

    boolean existsById (String id);

}
