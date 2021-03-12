package com.practice.mobydigital.exceptions;

import static com.practice.mobydigital.utils.ExceptionErrorMessage.PRODUCT_NOT_EXIST;

public class ProductNotExistsException extends RuntimeException{

    public ProductNotExistsException(){super(PRODUCT_NOT_EXIST);}
}
