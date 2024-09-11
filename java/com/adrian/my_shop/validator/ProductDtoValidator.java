package com.adrian.my_shop.validator;

import com.adrian.my_shop.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ProductDtoValidator {
    public void validate(ProductDto productDto, BindingResult bindingResult) {
        try {
            Double price = Double.parseDouble(productDto.getPrice());
            if (price <= 0) {
                FieldError fieldError = new FieldError("productDto", "price", "Price must be greater than 0.");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException e) {
            FieldError fieldError = new FieldError("productDto", "price", "Price must be a number.");
            bindingResult.addError(fieldError);
        }
    }
}
