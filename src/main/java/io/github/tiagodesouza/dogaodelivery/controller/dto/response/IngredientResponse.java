package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;

import java.math.BigDecimal;

public class IngredientResponse {

    @JsonProperty("ingredient_id")
    private final Long id;
    @JsonProperty("ingredient_name")
    private final String name;
    @JsonProperty("ingredient_price")
    private final BigDecimal price;


    public IngredientResponse(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
    }
}
