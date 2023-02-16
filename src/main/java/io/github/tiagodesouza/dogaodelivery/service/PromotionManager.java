package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Order;

import java.math.BigDecimal;

public interface PromotionManager {
    BigDecimal applyPromotions(Order order);
}
