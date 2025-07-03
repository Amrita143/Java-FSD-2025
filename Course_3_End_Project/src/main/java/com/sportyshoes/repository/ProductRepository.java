package com.sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sportyshoes.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
