package io.github.tiagodesouza.dogaodelivery.service.impl;

import io.github.tiagodesouza.dogaodelivery.model.AdditionalIngredient;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Order;
import io.github.tiagodesouza.dogaodelivery.model.OrderProduct;
import io.github.tiagodesouza.dogaodelivery.model.Product;
import io.github.tiagodesouza.dogaodelivery.service.CalculateAmountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateAmountServiceImpl implements CalculateAmountService {

    public BigDecimal calculateOrderAmount(Order order) {
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            Product product = orderProduct.getProduct();
            amount = amount.add(addProductIngredientsToAmount(product));
            amount = amount.add(addAdditionalIngredientsToAmount(orderProduct));
        }

        return amount;
    }

    public BigDecimal addProductIngredientsToAmount(Product product) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Ingredient ingredient : product.getIngredients()) {
            amount = amount.add(ingredient.getPrice());
        }
        return amount;
    }

    public BigDecimal addAdditionalIngredientsToAmount(OrderProduct orderProduct) {
        BigDecimal amount = BigDecimal.ZERO;
        if (!orderProduct.getAdditionalIngredients().isEmpty()) {
            for (AdditionalIngredient additionalIngredient : orderProduct.getAdditionalIngredients()) {
                BigDecimal additionalAmount = additionalIngredient.getIngredient().getPrice()
                        .multiply(new BigDecimal(additionalIngredient.getQuantity()));
                amount = amount.add(additionalAmount);
            }
        }
        return amount;
    }
}
