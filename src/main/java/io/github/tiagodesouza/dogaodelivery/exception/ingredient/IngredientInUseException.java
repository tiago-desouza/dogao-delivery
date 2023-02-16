package io.github.tiagodesouza.dogaodelivery.exception.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientInUseException extends RuntimeException {
    public IngredientInUseException(String message) {
        super(message);
    }
}
