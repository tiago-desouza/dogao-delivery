package io.github.tiagodesouza.dogaodelivery.service.impl.promotion;

import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;

import java.math.BigDecimal;

public interface PromotionStrategy {

    boolean isEligibleForPromotion(boolean hasIncludeIngredients, boolean hasExcludeIngredients);

    BigDecimal calculatePromotionDiscount(OrderProduct orderProduct, Promotion promotion);
}
