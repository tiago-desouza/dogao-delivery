package io.github.tiagodesouza.dogaodelivery.service.impl.promotion;

import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.TypePromotion;
import io.github.tiagodesouza.dogaodelivery.service.CalculateAmountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class LightPromotion implements PromotionStrategy {

    private final CalculateAmountService calculateAmountService;

    public LightPromotion(CalculateAmountService calculateAmountService) {
        this.calculateAmountService = calculateAmountService;
    }

    @Override
    public boolean isEligibleForPromotion(boolean hasIncludeIngredients, boolean hasExcludeIngredients) {
        return hasIncludeIngredients && !hasExcludeIngredients;
    }

    @Override
    public BigDecimal calculatePromotionDiscount(OrderProduct orderProduct, Promotion promotion) {
        if (TypePromotion.LIGHT == promotion.getTypePromotion()) {
            BigDecimal productPrice = calculateAmountService.addProductIngredientsToAmount(orderProduct.getProduct());
            BigDecimal additionalIngredientsPrice = calculateAmountService.addAdditionalIngredientsToAmount(orderProduct);
            BigDecimal totalOrderPrice = productPrice.add(additionalIngredientsPrice);
            BigDecimal discountPercentage = BigDecimal.valueOf(promotion.getDiscountPercentage())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN);

            return totalOrderPrice.multiply(discountPercentage).setScale(2, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO;
    }
}
