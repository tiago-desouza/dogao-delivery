package io.github.tiagodesouza.dogaodelivery.ProductCatalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AsJsonStringHelper {

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
