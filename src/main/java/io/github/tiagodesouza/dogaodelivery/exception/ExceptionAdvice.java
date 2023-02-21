package io.github.tiagodesouza.dogaodelivery.exception;

import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientAlreadyExists;
import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientInUseException;
import io.github.tiagodesouza.dogaodelivery.exception.ingredient.IngredientNotFoundException;
import io.github.tiagodesouza.dogaodelivery.exception.product.ProductAlreadyExists;
import io.github.tiagodesouza.dogaodelivery.exception.product.ProductNotFoundException;
import io.github.tiagodesouza.dogaodelivery.exception.promotion.InvalidPromotionException;
import io.github.tiagodesouza.dogaodelivery.exception.promotion.PromotionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IngredientAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleIngredientAlreadyExists(IngredientAlreadyExists ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IngredientInUseException.class)
    public ResponseEntity<ErrorResponse> handleIngredientInUseException(IngredientInUseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIngredientNotFoundException(IngredientNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleProductAlreadyExists(ProductAlreadyExists ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidPromotionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPromotionException(InvalidPromotionException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PromotionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePromotionNotFoundException(PromotionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
