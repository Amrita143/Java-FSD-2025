package com.sportyshoes.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.model.Product;
import com.sportyshoes.model.Purchase;
import com.sportyshoes.model.User;
import com.sportyshoes.repository.ProductRepository;
import com.sportyshoes.repository.PurchaseRepository;
import com.sportyshoes.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PurchaseRepository purchaseRepo;

    // Product management
    @GetMapping("/products")
    public List<Product> listProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product p) {
        return productRepo.save(p);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product p) {
        return productRepo.findById(id)
                .map(existing -> {
                    existing.setName(p.getName());
                    existing.setCategory(p.getCategory());
                    existing.setPrice(p.getPrice());
                    productRepo.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productRepo.findById(id)
                .map(p -> {
                    productRepo.delete(p);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // User list
    @GetMapping("/users")
    public List<User> listUsers(@RequestParam(required = false) String email) {
        if (email == null) {
            return userRepo.findAll();
        }
        return userRepo.findByEmailContainingIgnoreCase(email);
    }

    // Purchase reports
    @GetMapping("/purchases")
    public List<Purchase> purchases(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @RequestParam(required = false) String category) {
        if (date != null && category != null) {
            return purchaseRepo.findByDateAndProduct_Category(date, category);
        } else if (date != null) {
            return purchaseRepo.findByDate(date);
        } else if (category != null) {
            return purchaseRepo.findByProduct_Category(category);
        }
        return purchaseRepo.findAll();
    }
}
