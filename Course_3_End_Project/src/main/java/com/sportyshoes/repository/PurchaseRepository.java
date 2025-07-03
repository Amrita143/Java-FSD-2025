package com.sportyshoes.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.model.Product;
import com.sportyshoes.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByDate(LocalDate date);
    List<Purchase> findByProduct_Category(String category);
    List<Purchase> findByDateAndProduct_Category(LocalDate date, String category);
}
