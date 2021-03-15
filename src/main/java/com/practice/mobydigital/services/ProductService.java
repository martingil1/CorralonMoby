package com.practice.mobydigital.services;

import com.practice.mobydigital.exceptions.ProductAlreadyExistsException;
import com.practice.mobydigital.exceptions.ProductNotExistsException;
import com.practice.mobydigital.models.Dtos.AddProductDto;
import com.practice.mobydigital.models.Dtos.UpdateProductDto;
import com.practice.mobydigital.models.Product;
import com.practice.mobydigital.models.responses.ProductResponse;
import com.practice.mobydigital.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product add(AddProductDto p){

        Product product = Product.fromDTO(p);
        if(productRepository.existsById(product.getId())) throw new ProductAlreadyExistsException();
       // productRepository.getById(p.getIdProduct())
         //       .orElseThrow(ProductAlreadyExistsException::new);

        return productRepository.save(product);
    }

    public List<ProductResponse> products (){

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    public Product delete(String id){

        Product deletedProduct = productRepository.getById(id).orElseThrow(ProductNotExistsException::new);
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    public Product update(String id, UpdateProductDto productNew){

        Product oldProduct = productRepository.getById(id).orElseThrow(ProductNotExistsException::new);
        oldProduct.setName(productNew.getName());
        oldProduct.setTrademark(productNew.getTrademark());
        oldProduct.setPriceUnit(productNew.getPriceUnit());
        oldProduct.setCurrentStock(productNew.getCurrentStock());

        return productRepository.save(oldProduct);
    }

}
