package io.github.tiagodesouza.dogaodelivery.service.impl;

import io.github.tiagodesouza.dogaodelivery.exception.promotion.PromotionNotFoundException;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.model.enumerated.StatusPromotion;
import io.github.tiagodesouza.dogaodelivery.repository.PromotionRepository;
import io.github.tiagodesouza.dogaodelivery.service.IngredientService;
import io.github.tiagodesouza.dogaodelivery.service.PromotionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final IngredientService ingredientService;

    public PromotionServiceImpl(PromotionRepository promotionRepository, IngredientService ingredientService) {
        this.promotionRepository = promotionRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Promotion create(Promotion promotion) {
        validatePromotion(promotion);

        return promotionRepository.save(promotion);
    }

    @Override
    public Optional<Promotion> findPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    @Override
    public List<Promotion> findAllPromotions(int page, int size) {
        return promotionRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }

    @Override
    public void enablePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException("Promotion not found with id: " + id));

        promotion.setStatusPromotion(StatusPromotion.ACTIVE);

        promotionRepository.save(promotion);
    }

    @Override
    public void disablePromotion(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new PromotionNotFoundException("Promotion not found with id: " + id));

        promotion.setStatusPromotion(StatusPromotion.DISABLE);

        promotionRepository.save(promotion);
    }

    private void validatePromotion(Promotion promotion) {
        if (promotion.getIncludeIngredient() != null) {
            Ingredient includeIngredient = ingredientService.findByName(promotion.getIncludeIngredient().getName());
            promotion.setIncludeIngredient(includeIngredient);
        }
        if (promotion.getExcludeIngredient() != null) {
            Ingredient excludeIngredient = ingredientService.findByName(promotion.getExcludeIngredient().getName());
            promotion.setExcludeIngredient(excludeIngredient);
        }
    }

}
