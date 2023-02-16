package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.AdditionalIngredient;
import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {
    @JsonProperty("order_products")
    private List<OrderProductRequest> orderProductRequests;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderProductRequest> orderProductRequests) {
        this.orderProductRequests = orderProductRequests;
    }

    public Order toEntity() {
        Order order = new Order();
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (OrderProductRequest orderProductRequest : this.orderProductRequests) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.getProduct().setName(orderProductRequest.getProductName());

            List<AdditionalIngredient> additionalIngredients = new ArrayList<>();
            if (orderProductRequest.getAdditionalIngredientRequests() != null) {
                for (AdditionalIngredientRequest additionalIngredientRequest : orderProductRequest.getAdditionalIngredientRequests()) {
                    AdditionalIngredient additionalIngredient = new AdditionalIngredient();
                    additionalIngredient.getIngredient().setName(additionalIngredientRequest.getIngredientName());
                    additionalIngredient.setQuantity(additionalIngredientRequest.getQuantity());
                    additionalIngredients.add(additionalIngredient);
                }
            }
            orderProduct.setAdditionalIngredients(additionalIngredients);
            orderProducts.add(orderProduct);
        }

        order.setOrderProducts(orderProducts);
        return order;
    }
}
