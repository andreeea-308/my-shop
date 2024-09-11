package com.adrian.my_shop.mapper;

import com.adrian.my_shop.dto.ProductDto;
import com.adrian.my_shop.entity.ProductEntity;
import com.adrian.my_shop.enums.CategoryType;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ProductMapper {
    public ProductEntity map(ProductDto productDto, MultipartFile imageFile){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setPrice(Double.parseDouble(productDto.getPrice()));
        productEntity.setQuantity(Integer.parseInt(productDto.getQuantity()));
        productEntity.setCategoryType(CategoryType.valueOf(productDto.getCategory()));
        productEntity.setDescription(productDto.getDescription());
        productEntity.setImage(convertToByteArray(imageFile));
        return productEntity;
    }
    public ProductDto map(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice().toString());
        productDto.setQuantity(productEntity.getQuantity().toString());
        productDto.setCategory(String.valueOf(productEntity.getCategoryType()));
        productDto.setDescription(productEntity.getDescription());
        productDto.setImage(Base64.encodeBase64String(productEntity.getImage()));
        productDto.setId(productEntity.getId().toString());
        return productDto;
    }

    private byte[] convertToByteArray (MultipartFile multipartFile){
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
