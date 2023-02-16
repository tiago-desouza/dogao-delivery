package io.github.tiagodesouza.dogaodelivery.service.impl.promotion;

import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.TypePromotion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuantityAbovePromotion implements PromotionStrategy {

    @Override
    public boolean isEligibleForPromotion(boolean hasIncludeIngredients, boolean hasExcludeIngredients) {
        return hasIncludeIngredients;
    }

    @Override
    public BigDecimal calculatePromotionDiscount(OrderProduct orderProduct, Promotion promotion) {
        if (!TypePromotion.QUANTITY_ABOVE.equals(promotion.getTypePromotion())) {
            return BigDecimal.ZERO;
        }

        List<Ingredient> ingredients = validateIngredientsPromotion(orderProduct, promotion);
        int freeIngredient = calculateFreeIngredients(promotion, ingredients);
        BigDecimal ingredientsPrice = getPriceIngredient(ingredients);

        return ingredientsPrice.multiply(BigDecimal.valueOf(freeIngredient));
    }

    private static List<Ingredient> validateIngredientsPromotion(OrderProduct orderProduct, Promotion promotion) {
        List<Ingredient> allIngredients = new ArrayList<>();
        allIngredients.addAll(orderProduct.getProduct().getIngredients());
        allIngredients.addAll(orderProduct.getAdditionalIngredients().stream()
                .flatMap(additionalIngredient ->
                        Collections.nCopies(additionalIngredient.getQuantity(), additionalIngredient.getIngredient())
                                .stream())
                .collect(Collectors.toList()));

        return allIngredients.stream()
                .filter(ingredient -> ingredient.equals(promotion.getIncludeIngredient()))
                .collect(Collectors.toList());
    }

    private static int calculateFreeIngredients(Promotion promotion, List<Ingredient> ingredients) {
        int count = ingredients.size();
        int freeIngredient = 0;
        int quantityToBuy = promotion.getQuantity();

        while (count >= quantityToBuy) {
            freeIngredient++;
            count -= quantityToBuy;
        }
        return freeIngredient;
    }

    private static BigDecimal getPriceIngredient(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getPrice)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }
}
