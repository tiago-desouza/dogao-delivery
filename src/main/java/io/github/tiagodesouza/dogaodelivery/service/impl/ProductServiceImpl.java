package io.github.tiagodesouza.dogaodelivery.service.impl;


import io.github.tiagodesouza.dogaodelivery.exception.product.ProductAlreadyExists;
import io.github.tiagodesouza.dogaodelivery.exception.promotion.PromotionNotFoundException;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Product;
import io.github.tiagodesouza.dogaodelivery.repository.ProductRepository;
import io.github.tiagodesouza.dogaodelivery.service.IngredientService;
import io.github.tiagodesouza.dogaodelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final IngredientService ingredientService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, IngredientService ingredientService) {
        this.productRepository = productRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Product createProduct(Product product) {
        validateIfExistingProductByName(product.getName());
        validateIngredientsExist(product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productToUpdate = findProductById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setIngredients(product.getIngredients());

        return createProduct(productToUpdate);
    }

    @Override
    public List<Product> findAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        findProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new PromotionNotFoundException("Product not found with name: " + name));
    }

    private void validateIfExistingProductByName(String name) {
        Optional<Product> productSaved = productRepository.findByName(name);
        if (productSaved.isPresent()) throw new ProductAlreadyExists();
    }

    private void validateIngredientsExist(Product product) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : product.getIngredients()) {
            Ingredient ingredientToUpdate = ingredientService.findByName(ingredient.getName());
            ingredients.add(ingredientToUpdate);
        }
        product.setIngredients(ingredients);
    }
}
