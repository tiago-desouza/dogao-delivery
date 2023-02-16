package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderProductRequest {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("additional_ingredients")
    private List<AdditionalIngredientRequest> additionalIngredientRequests;

    public OrderProductRequest() {
    }

    public OrderProductRequest(String productName, List<AdditionalIngredientRequest> additionalIngredientRequests) {
        this.productName = productName.toLowerCase();
        this.additionalIngredientRequests = additionalIngredientRequests;
    }

    public String getProductName() {
        return productName;
    }

    public List<AdditionalIngredientRequest> getAdditionalIngredientRequests() {
        return additionalIngredientRequests;
    }
}
