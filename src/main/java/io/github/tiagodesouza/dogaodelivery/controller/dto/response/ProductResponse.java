package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    @JsonProperty("product_id")
    private final Long id;
    @JsonProperty("product_name")
    private final String name;
    @JsonProperty("product_ingredients")
    private final List<IngredientResponse> ingredientResponses;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.ingredientResponses = product.getIngredients().stream()
                .map(IngredientResponse::new)
                .collect(Collectors.toList());
    }
}
