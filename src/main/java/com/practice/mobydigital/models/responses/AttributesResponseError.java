package com.practice.mobydigital.models.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttributesResponseError {

    private final Map<String, List<String>> errors;

    public static AttributesResponseError fromMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField().replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });

        return AttributesResponseError.builder().errors(errors).build();
    }

}

