package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;

import java.util.List;
import java.util.stream.Collectors;

public class OrderProductResponse {

    @JsonProperty("product")
    private final ProductResponse productResponse;
    @JsonProperty("additional_Ingredients")
    private final List<AdditionalIngredientResponse> additionalIngredientResponses;

    public OrderProductResponse(OrderProduct orderProduct) {
        this.productResponse = new ProductResponse(orderProduct.getProduct());
        this.additionalIngredientResponses = orderProduct.getAdditionalIngredients().stream()
                .map(AdditionalIngredientResponse::new)
                .collect(Collectors.toList());
    }
}
