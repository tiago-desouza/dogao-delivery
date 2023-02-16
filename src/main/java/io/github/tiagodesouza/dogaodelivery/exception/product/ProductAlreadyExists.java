package io.github.tiagodesouza.dogaodelivery.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductAlreadyExists extends RuntimeException{
    public ProductAlreadyExists() {
        super("Product Already Exists");
    }
}
