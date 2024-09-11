package com.adrian.my_shop.service;

import com.adrian.my_shop.dto.CartDto;
import com.adrian.my_shop.dto.CartEntryDto;
import com.adrian.my_shop.dto.SelectionDto;
import com.adrian.my_shop.entity.Cart;
import com.adrian.my_shop.entity.CartEntry;
import com.adrian.my_shop.entity.ProductEntity;
import com.adrian.my_shop.mapper.CartEntryMapper;
import com.adrian.my_shop.repository.CartEntryRepository;
import com.adrian.my_shop.repository.CartRepository;
import com.adrian.my_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Autowired
    private CartEntryMapper cartEntryMapper;

    public void addToCart(String loggedInUserEmail, SelectionDto selectionDto, String productId){
        Cart cart = cartRepository.findByUserEntityEmail(loggedInUserEmail);
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(Integer.valueOf(productId));
        if(optionalProductEntity.isEmpty()){
            throw new RuntimeException("Product does not exist!!!");
        }
        ProductEntity productEntity = optionalProductEntity.get();
        CartEntry cartEntry = createCartEntry(selectionDto, cart, productEntity);
        cartEntryRepository.save(cartEntry);
    }

    private CartEntry createCartEntry(SelectionDto selectionDto, Cart cart, ProductEntity productEntity) {
        Integer selectedQuantity = Integer.valueOf(selectionDto.getQuantity());

        CartEntry cartEntry = new CartEntry();
        cartEntry.setCart(cart);
        cartEntry.setProduct(productEntity);
        cartEntry.setSelectedQuantity(selectedQuantity);
        return cartEntry;
    }

    public CartDto getCartDtoFor(String authenticatedUserEmail) {
        List<CartEntry> cartEntryList = cartEntryRepository.findByCartUserEntityEmail(authenticatedUserEmail);
        List<CartEntryDto> cartEntryDtoList = createCartEntryDtoList(cartEntryList);
        Double totalPrice = calculateTotalPrice(cartEntryList);


        CartDto cartDto = new CartDto();
        cartDto.setCartEntryDtoList(cartEntryDtoList);
        cartDto.setTotalPrice(String.valueOf(totalPrice));
        return cartDto;
    }

    private Double calculateTotalPrice(List<CartEntry> cartEntryList) {
        Double totalPrice = 0.0;
        for(CartEntry cartEntry: cartEntryList){
            double cartEntryPrice = cartEntry.getSelectedQuantity() * cartEntry.getProduct().getPrice();
            totalPrice += cartEntryPrice;
        }
        return totalPrice;
    }

    private List<CartEntryDto> createCartEntryDtoList(List<CartEntry> cartEntryList) {
        List<CartEntryDto> cartEntryDtoList = cartEntryList.stream()
                .map(cartEntry -> cartEntryMapper.map(cartEntry))
                .toList();
        return cartEntryDtoList;
    }
}
