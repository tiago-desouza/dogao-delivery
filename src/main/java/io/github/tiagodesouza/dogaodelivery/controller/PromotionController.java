package io.github.tiagodesouza.dogaodelivery.controller;

import io.github.tiagodesouza.dogaodelivery.controller.dto.request.PromotionRequest;
import io.github.tiagodesouza.dogaodelivery.controller.dto.response.PromotionResponse;
import io.github.tiagodesouza.dogaodelivery.model.Promotion;
import io.github.tiagodesouza.dogaodelivery.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody @Valid PromotionRequest promotionRequest) {
        Promotion promotionSaved = promotionService.create(promotionRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new PromotionResponse(promotionSaved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponse> findPromotionById(@PathVariable("id") Long id) {
        return promotionService.findPromotionById(id)
                .map(promotion -> ResponseEntity.ok(new PromotionResponse(promotion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PromotionResponse>> findAllPromotions(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<PromotionResponse> promotionResponses = promotionService.findAllPromotions(page, size)
                .stream().map(PromotionResponse::new)
                .collect(Collectors.toList());

        if (promotionResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(promotionResponses);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}/enable")
    public void enablePromotion(@PathVariable("id") Long id) {
        promotionService.enablePromotion(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}/disable")
    public void disablePromotion(@PathVariable("id") Long id) {
        promotionService.disablePromotion(id);
    }
}
