package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product p = this.repository.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating product: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            return ResponseEntity.ok(this.repository.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not fetch all products.. " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleProduct(@PathVariable int id) {
        try {
            return ResponseEntity.ok(this.repository.getSingleProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error.. " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.repository.updateProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find product to update " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            if (this.repository.deleteProduct(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Product removed.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find product to delete, " + e.getMessage());
        }
        return ResponseEntity.badRequest().body("Error");
    }

}
