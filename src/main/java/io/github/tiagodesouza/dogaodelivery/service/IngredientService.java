package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient findByName(String name);

    Ingredient updateIngredient(Long id, Ingredient ingredient);

    List<Ingredient> findAllIngredients(int page, int size);

    Ingredient findIngredientById(Long id);

    void deleteIngredient(Long id);
}
