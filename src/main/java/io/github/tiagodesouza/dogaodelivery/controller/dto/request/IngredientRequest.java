package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class IngredientRequest {

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be Empty.")
    @JsonProperty("ingredient_name")
    private final String name;

    @NotNull(message = "Price cannot be null.")
    @PositiveOrZero
    @JsonProperty("ingredient_price")
    private final BigDecimal price;

    public IngredientRequest(String name, BigDecimal price) {
        this.name = name.toLowerCase();
        this.price = price;
    }

    public Ingredient toEntity() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(this.name);
        ingredient.setPrice(this.price);
        return ingredient;
    }
}
