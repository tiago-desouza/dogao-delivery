package io.github.tiagodesouza.dogaodelivery.service.impl.promotion;

import io.github.tiagodesouza.dogaodelivery.model.AdditionalIngredient;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.StatusPromotion;
import io.github.tiagodesouza.dogaodelivery.repository.PromotionRepository;
import io.github.tiagodesouza.dogaodelivery.service.PromotionManager;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromotionManagerImpl implements PromotionManager {

    private final PromotionRepository promotionRepository;
    private final List<PromotionStrategy> promotionStrategies;

    public PromotionManagerImpl(PromotionRepository promotionRepository, List<PromotionStrategy> promotionStrategies) {
        this.promotionRepository = promotionRepository;
        this.promotionStrategies = promotionStrategies;
    }

    @Override
    public BigDecimal applyPromotions(Order order) {
        List<Promotion> promotions = promotionRepository.findAllByStatusPromotion(StatusPromotion.ACTIVE);
        List<BigDecimal> discounts = new ArrayList<>();
        for (Promotion promotion : promotions) {
            for (OrderProduct orderProduct : order.getOrderProducts()) {
                boolean hasIncludeIngredients = orderContainsAllIngredients(orderProduct, promotion.getIncludeIngredient());
                boolean hasExcludeIngredients = orderContainsAllIngredients(orderProduct, promotion.getExcludeIngredient());
                for (PromotionStrategy promotionStrategy : promotionStrategies) {
                    boolean eligibleForPromotion = promotionStrategy.
                            isEligibleForPromotion(hasIncludeIngredients, hasExcludeIngredients);
                    if (eligibleForPromotion) {
                        discounts.add(promotionStrategy.calculatePromotionDiscount(orderProduct, promotion));
                    }
                }
            }
        }
        return discounts.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    private boolean orderContainsAllIngredients(OrderProduct orderProduct, Ingredient ingredient) {
        return new HashSet<>(getAllIngredientsInOrderProduct(orderProduct)).contains(ingredient);
    }

    private List<Ingredient> getAllIngredientsInOrderProduct(OrderProduct orderProduct) {
        List<Ingredient> allIngredients = new ArrayList<>();
        allIngredients.addAll(orderProduct.getProduct().getIngredients());
        allIngredients.addAll(orderProduct.getAdditionalIngredients().stream()
                .map(AdditionalIngredient::getIngredient)
                .collect(Collectors.toList()));
        return allIngredients;
    }
}
