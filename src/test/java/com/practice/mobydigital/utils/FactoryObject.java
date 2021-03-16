package com.practice.mobydigital.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.practice.mobydigital.models.Dtos.AddProductDto;
import com.practice.mobydigital.models.Dtos.UpdateProductDto;
import com.practice.mobydigital.models.Product;
import com.practice.mobydigital.models.responses.ProductResponse;

public interface FactoryObject {

    default String createJsonRequest(Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(object);
    }

    default Product createProduct() {
        return Product.builder()
                .id("12")
                .name("product test")
                .trademark("trademark test")
                .priceUnit(12.0)
                .currentStock(12)
                .build();
    }

    default ProductResponse createProductResponse() {

        return ProductResponse.builder()
                .id("12")
                .name("product test")
                .trademark("trademark test")
                .priceUnit(12.0)
                .currentStock(12)
                .build();
    }

    default AddProductDto createAddProductDto() {

        return AddProductDto.builder()
                .idProduct("12")
                .name("product test")
                .trademark("trademark test")
                .priceUnit(12.0)
                .currentStock(12)
                .build();
    }

    default UpdateProductDto createUpdateProductDto() {

        return UpdateProductDto.builder()
                .name("product test")
                .trademark("trademark test")
                .priceUnit(12.0)
                .currentStock(12)
                .build();
    }

}
