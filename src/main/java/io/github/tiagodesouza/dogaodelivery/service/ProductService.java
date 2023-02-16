package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    List<Product> findAllProducts(int page, int size);

    Product findProductById(Long id);

    void delete(Long id);

    Product findProductByName(String name);
}
