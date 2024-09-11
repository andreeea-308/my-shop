package com.adrian.my_shop.service;

import com.adrian.my_shop.dto.ProductDto;
import com.adrian.my_shop.entity.ProductEntity;
import com.adrian.my_shop.mapper.ProductMapper;
import com.adrian.my_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    public void addProduct(ProductDto productDto, MultipartFile imageFile){
        ProductEntity entity = productMapper.map(productDto, imageFile);
        productRepository.save(entity);
    }
    public List<ProductDto> getAllProductDtoList(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList.stream()
                .map(productMapper::map)
                .toList();
    }
    public Optional<ProductDto> getProductDtoByProductId(String productId){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(Integer.parseInt(productId));
        if (optionalProductEntity.isEmpty()){
            return Optional.empty();
        }
        ProductEntity productEntity = optionalProductEntity.get();
        ProductDto productDto = productMapper.map(productEntity);
        return Optional.of(productDto);
    }
}
