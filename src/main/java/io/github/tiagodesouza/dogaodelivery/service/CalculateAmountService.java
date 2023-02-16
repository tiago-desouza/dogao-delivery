package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Product;

import java.math.BigDecimal;

public interface CalculateAmountService {

    BigDecimal calculateOrderAmount(Order order);

    BigDecimal addProductIngredientsToAmount(Product product);

    BigDecimal addAdditionalIngredientsToAmount(OrderProduct orderProduct);
}
