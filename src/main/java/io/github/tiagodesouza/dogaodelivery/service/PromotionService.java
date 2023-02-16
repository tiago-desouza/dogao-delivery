package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PromotionService {

    Promotion create(Promotion promotion);

    Optional<Promotion> findPromotionById(Long id);

    List<Promotion> findAllPromotions(int page, int size);

    void enablePromotion(Long id);

    void disablePromotion(Long id);
}
