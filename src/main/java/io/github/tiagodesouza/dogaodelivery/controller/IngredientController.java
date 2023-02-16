package io.github.tiagodesouza.dogaodelivery.controller;

import io.github.tiagodesouza.dogaodelivery.controller.dto.request.IngredientRequest;
import io.github.tiagodesouza.dogaodelivery.controller.dto.response.IngredientResponse;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/v1/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(
            @RequestBody @Valid IngredientRequest ingredientRequest
    ) {
        Ingredient ingredient = ingredientService.createIngredient(ingredientRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new IngredientResponse(ingredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(
            @PathVariable("id") Long id,
            @RequestBody @Valid IngredientRequest ingredientRequest
    ) {
        Ingredient ingredient = ingredientService.updateIngredient(id, ingredientRequest.toEntity());
        return ResponseEntity.ok(new IngredientResponse(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> findAllIngredients(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        List<IngredientResponse> ingredientResponses = ingredientService.findAllIngredients(page, size)
                .stream().map(IngredientResponse::new)
                .collect(Collectors.toList());

        if (ingredientResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingredientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> findIngredientById(
            @PathVariable(name = "id") Long id
    ) {
        Ingredient ingredient = ingredientService.findIngredientById(id);
        return ResponseEntity.ok(new IngredientResponse(ingredient));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteIngredient(
            @PathVariable(name = "id") Long id
    ) {
        ingredientService.deleteIngredient(id);
    }
}
