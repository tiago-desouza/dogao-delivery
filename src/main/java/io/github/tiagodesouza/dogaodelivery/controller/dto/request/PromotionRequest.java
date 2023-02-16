package io.github.tiagodesouza.dogaodelivery.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.StatusPromotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.TypePromotion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PromotionRequest {

    @NotNull(message = "Title cannot be null.")
    @NotEmpty(message = "Title cannot be Empty.")
    @JsonProperty("promotion_title")
    private final String title;

    @NotNull(message = "Description cannot be null.")
    @NotEmpty(message = "Description cannot be Empty.")
    @JsonProperty("promotion_description")
    private final String description;

    @JsonProperty("promotion_include_ingredient")
    private final String includeIngredient;

    @JsonProperty("promotion_exclude_ingredient")
    private final String excludeIngredient;

    @NotNull(message = "Type promotion cannot be null.")
    @JsonProperty("promotion_type")
    private final TypePromotion typePromotion;

    @JsonProperty("promotion_discount_percentage")
    private final Integer discountPercentage;

    @JsonProperty("promotion_quantity")
    private final Integer quantity;

    public PromotionRequest(String title, String description, String includeIngredient,
                            String excludeIngredient, TypePromotion typePromotion,
                            Integer discountPercentage, Integer quantity
    ) {
        this.title = title;
        this.description = description;
        this.includeIngredient = includeIngredient;
        this.excludeIngredient = excludeIngredient;
        this.typePromotion = typePromotion;
        this.discountPercentage = discountPercentage;
        this.quantity = quantity;
    }

    public Promotion toEntity() {
        Promotion promotion = new Promotion();
        promotion.setTitle(this.title.toLowerCase());
        promotion.setDescription(this.description);
        if (this.includeIngredient != null) {
            promotion.setIncludeIngredient(new Ingredient(this.includeIngredient.toLowerCase()));
        }
        if (this.excludeIngredient != null) {
            promotion.setExcludeIngredient(new Ingredient(this.excludeIngredient.toLowerCase()));
        }
        promotion.setTypePromotion(this.typePromotion);
        promotion.setStatusPromotion(StatusPromotion.DISABLE);
        promotion.setDiscountPercentage(this.discountPercentage);
        promotion.setQuantity(this.quantity);
        return promotion;
    }
}
