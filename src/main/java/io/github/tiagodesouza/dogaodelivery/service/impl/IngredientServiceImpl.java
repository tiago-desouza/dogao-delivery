package io.github.tiagodesouza.dogaodelivery.service.impl;

import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientAlreadyExists;
import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientInUseException;
import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientNotFoundException;
import io.github.tiagodesouza.dogaodelivery.model.Ingredient;
import io.github.tiagodesouza.dogaodelivery.repository.IngredientRepository;
import io.github.tiagodesouza.dogaodelivery.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        validateIfExistingIngredientByName(ingredient);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found with name " + name));
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient ingredientToUpdate = findIngredientById(id);
        ingredientToUpdate.setName(ingredient.getName());
        ingredientToUpdate.setPrice(ingredient.getPrice());
        return ingredientRepository.save(ingredientToUpdate);
    }

    @Override
    public List<Ingredient> findAllIngredients(int page, int size) {
        return ingredientRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }

    @Override
    public Ingredient findIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found with id: " + id));
    }

    @Override
    public void deleteIngredient(Long id) {
        try{
            findIngredientById(id);
            ingredientRepository.deleteById(id);
        } catch (Exception e) {
            throw new IngredientInUseException("The ingredient is linked to a product or order.");
        }
    }

    private void validateIfExistingIngredientByName(Ingredient ingredient) {
        Optional<Ingredient> existingIngredient = ingredientRepository.findByName(ingredient.getName());
        if (existingIngredient.isPresent()) throw new IngredientAlreadyExists();
    }
}
