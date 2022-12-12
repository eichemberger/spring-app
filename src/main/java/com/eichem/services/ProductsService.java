package com.eichem.services;

import com.eichem.entities.Product;
import com.eichem.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductsService {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private void validateProduct(Product product) {
        Assert.notNull(product, "Product cannot be empty");
        Assert.notNull(product.getName(), "Product name cannot be empty");
        Assert.notNull(product.getPrice(), "Product price cannot be empty");
        Assert.isTrue(product.getPrice() > 0, "Product price must be greater than 0");

        if (product.getQuantity() == null) {
            product.setQuantity(0);
        }
        Assert.isTrue(product.getQuantity() >= 0, "Product quantity must be greater than or equal to 0");
    }

}
