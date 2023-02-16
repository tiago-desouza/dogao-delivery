package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

    @JsonProperty("order_id")
    private final Long id;
    @JsonProperty("order_Products")
    private final List<OrderProductResponse> orderProductResponses;
    @JsonProperty("order_amount")
    private final BigDecimal amount;
    @JsonProperty("order_discount")
    private final BigDecimal discount;
    @JsonProperty("order_total_order_amount")
    private final BigDecimal totalOrderAmount;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.orderProductResponses = order.getOrderProducts().stream()
                .map(OrderProductResponse::new)
                .collect(Collectors.toList());
        this.amount = order.getAmount();
        this.discount = order.getDiscount();
        this.totalOrderAmount = order.getTotalOrderAmount();
    }
}
