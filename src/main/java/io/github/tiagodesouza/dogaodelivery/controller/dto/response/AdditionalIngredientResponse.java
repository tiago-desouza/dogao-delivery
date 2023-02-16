package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.AdditionalIngredient;

import java.math.BigDecimal;

public class AdditionalIngredientResponse {

    @JsonProperty("ingredient_id")
    private final Long id;
    @JsonProperty("ingredient_name")
    private final String name;
    @JsonProperty("ingredient_price")
    private final BigDecimal price;
    @JsonProperty("ingredient_quantity")
    private final Integer quantity;

    public AdditionalIngredientResponse(AdditionalIngredient additionalIngredient) {
        this.id = additionalIngredient.getIngredient().getId();
        this.name = additionalIngredient.getIngredient().getName();
        this.price = additionalIngredient.getIngredient().getPrice();
        this.quantity = additionalIngredient.getQuantity();
    }
}
