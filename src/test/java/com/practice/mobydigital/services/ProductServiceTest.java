package com.practice.mobydigital.services;

import com.practice.mobydigital.exceptions.ProductAlreadyExistsException;
import com.practice.mobydigital.exceptions.ProductNotExistsException;
import com.practice.mobydigital.models.Dtos.AddProductDto;
import com.practice.mobydigital.models.Product;
import com.practice.mobydigital.models.responses.ProductResponse;
import com.practice.mobydigital.repositories.ProductRepository;
import com.practice.mobydigital.utils.FactoryObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest implements FactoryObject {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void openMocks() {
       MockitoAnnotations.openMocks(this);
       productService = new ProductService(productRepository);
    }

    @Test
    public void addTestOk() {
        AddProductDto newProduct = createAddProductDto();
        Product product = createProduct();
        when(productRepository.existsById("12")).thenReturn(false);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        assertEquals(product, productService.add(newProduct));
    }

    @Test(expected = ProductAlreadyExistsException.class)
    public void testAddProductAlreadyExistsException() {
        AddProductDto newProduct = createAddProductDto();

        when(productRepository.existsById("12")).thenReturn(true);
        productService.add(newProduct);
    }

    @Test
    public void productsTestOk(){

        List<ProductResponse> products = Collections.singletonList(createProductResponse());

        when(productRepository.findAll()).thenReturn(Collections.singletonList(createProduct()));
        assertEquals(products, productService.products());
    }

    @Test
    public void deleteTestOk(){

        Product product = createProduct();
        when(productRepository.getById("1")).thenReturn(Optional.ofNullable(product));

        assertEquals(product, productService.delete("1"));
    }

    @Test(expected = ProductNotExistsException.class)
    public void testDeleteProductNotExistsException() {

        when(productRepository.getById("10")).thenReturn(Optional.empty());
        productService.delete("10");
    }

    @Test
    public void updateTestOk(){

        Product product = createProduct();
        when(productRepository.getById("12")).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertEquals(product, productService.update("12", createUpdateProductDto()));
    }

    @Test(expected = ProductNotExistsException.class)
    public void testUpdateProductNotExistsException() {

        when(productRepository.getById("10")).thenReturn(Optional.empty());
        productService.update("10", createUpdateProductDto());
    }
}
