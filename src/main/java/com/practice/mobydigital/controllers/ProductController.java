package com.practice.mobydigital.controllers;

import com.practice.mobydigital.models.Dtos.AddProductDto;
import com.practice.mobydigital.models.Dtos.UpdateProductDto;
import com.practice.mobydigital.models.responses.ProductResponse;
import com.practice.mobydigital.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/")
    public ResponseEntity addProduct(@Valid @RequestBody AddProductDto productDto) {

        return ResponseEntity.ok(productService.add(productDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {

        List<ProductResponse> productResponses = productService.products();

        return ResponseEntity.ok(productResponses);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<ProductResponse> remove(@PathVariable(value = "product-id") String idProduct) {

        return ResponseEntity.ok(ProductResponse.fromProduct(productService.delete(idProduct)));
    }

    @PatchMapping("/{product-id}")
    public ResponseEntity<ProductResponse> partialUpdate(@PathVariable(value = "product-id") String idProduct, @RequestBody @Valid UpdateProductDto product) {

        return ResponseEntity.ok(ProductResponse.fromProduct(productService.update(idProduct, product)));
    }

}
