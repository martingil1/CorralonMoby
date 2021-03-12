package com.practice.mobydigital.models.Dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.practice.mobydigital.utils.DtosErrorMessage.MANDATORY_NAME;
import static com.practice.mobydigital.utils.DtosErrorMessage.MANDATORY_TRADEMARK;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateProductDto {

    @NotBlank(message = MANDATORY_NAME)
    private String name;

    @NotBlank(message = MANDATORY_TRADEMARK)
    private String trademark;

    @NotNull
    private Double priceUnit;

    @NotNull
    private Integer currentStock;
}
