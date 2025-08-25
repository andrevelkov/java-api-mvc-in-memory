package com.booleanuk.api.products;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {}

    public Product createProduct(Product product) {
        product.setId(idCounter++);
        this.productList.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return this.productList;
    }

    public Product getSingleProduct(int id) {
        return productList.stream()
                .filter(product ->  product.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public Product updateProduct(int id) {
        Product product = productList.stream()
                                    .filter(p -> p.getId() == id)
                                    .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        // UPDATE PRODUCT info and then return product wowoww
        // product.setName();
        return product;
    }

    public boolean deleteProduct(int id) {
        Product product = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        return productList.remove(product);
    }
}
