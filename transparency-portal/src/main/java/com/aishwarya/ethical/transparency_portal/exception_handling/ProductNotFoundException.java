package com.aishwarya.ethical.transparency_portal.exception_handling;

public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException(String message) {
        super(ErrorCode.PRODUCT_NOT_FOUND, message);
    }
}
