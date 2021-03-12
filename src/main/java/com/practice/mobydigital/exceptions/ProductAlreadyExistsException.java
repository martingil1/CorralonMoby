package com.practice.mobydigital.exceptions;

import static com.practice.mobydigital.utils.ExceptionErrorMessage.THE_PRODUCT_ALREADY_EXIST;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException(){
        super(THE_PRODUCT_ALREADY_EXIST);
    }

}
