package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionalIngredientRequest {

    @JsonProperty("ingredient_name")
    private String ingredientName;
    @JsonProperty("ingredient_quantity")
    private Integer quantity;

    public AdditionalIngredientRequest() {
    }

    public AdditionalIngredientRequest(String ingredientName, Integer quantity) {
        this.ingredientName = ingredientName.toLowerCase();
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
