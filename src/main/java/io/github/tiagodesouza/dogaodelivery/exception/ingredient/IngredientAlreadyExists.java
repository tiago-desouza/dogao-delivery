package io.github.tiagodesouza.dogaodelivery.exception.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientAlreadyExists extends RuntimeException{
    public IngredientAlreadyExists() {
        super("Ingredient Already Exists");
    }
}
