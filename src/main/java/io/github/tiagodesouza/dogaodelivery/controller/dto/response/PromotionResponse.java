package io.github.tiagodesouza.dogaodelivery.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.StatusPromotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.TypePromotion;

public class PromotionResponse {

    @JsonProperty("promotion_id")
    private final Long id;
    @JsonProperty("promotion_title")
    private final String title;
    @JsonProperty("promotion_description")
    private final String description;
    @JsonProperty("promotion_include_ingredient")
    private final IngredientResponse includeIngredient;
    @JsonProperty("promotion_exclude_ingredient")
    private final IngredientResponse excludeIngredient;
    @JsonProperty("promotion_type")
    private final TypePromotion typePromotion;
    @JsonProperty("promotion_status")
    private final StatusPromotion statusPromotion;
    @JsonProperty("promotion_discount_percentage")
    private final Integer discountPercentage;
    @JsonProperty("promotion_quantity")
    private final Integer quantity;

    public PromotionResponse(Promotion promotion) {
        this.id = promotion.getId();
        this.title = promotion.getTitle();
        this.description = promotion.getDescription();
        this.includeIngredient = createIngredientResponse(promotion.getIncludeIngredient());
        this.excludeIngredient = createIngredientResponse(promotion.getExcludeIngredient());
        this.typePromotion = promotion.getTypePromotion();
        this.statusPromotion = promotion.getStatusPromotion();
        this.discountPercentage = promotion.getDiscountPercentage();
        this.quantity = promotion.getQuantity();
    }

    private IngredientResponse createIngredientResponse(Ingredient ingredient) {
        return ingredient != null ? new IngredientResponse(ingredient) : null;
    }
}
