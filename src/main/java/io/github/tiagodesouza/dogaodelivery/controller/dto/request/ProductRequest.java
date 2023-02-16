package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProductRequest {

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be Empty.")
    @JsonProperty("product_name")
    private final String name;

    @NotNull(message = "Ingredient names cannot be null.")
    @NotEmpty(message = "Ingredient names cannot be null.")
    @JsonProperty("product_ingredients_name")
    private final List<String> ingredientNames;

    public ProductRequest(String name, List<String> ingredientNames) {
        this.name = name.toLowerCase();
        this.ingredientNames = ingredientNames;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (String name : ingredientNames) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(name.toLowerCase());

            ingredients.add(ingredient);
        }

        product.setIngredients(ingredients);
        return product;
    }

}
