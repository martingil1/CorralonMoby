package com.practice.mobydigital.models.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.practice.mobydigital.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductResponse {

    private String id;

    private String name;

    private String trademark;

    private Double priceUnit;

    private Integer currentStock;

    public static ProductResponse fromProduct(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .trademark(product.getTrademark())
                .priceUnit(product.getPriceUnit())
                .currentStock(product.getCurrentStock())
                .build();

    }

}
