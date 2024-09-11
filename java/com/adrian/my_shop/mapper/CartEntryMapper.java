package com.adrian.my_shop.mapper;

import com.adrian.my_shop.dto.CartEntryDto;
import com.adrian.my_shop.entity.CartEntry;
import org.springframework.stereotype.Component;

@Component
public class CartEntryMapper {
    public CartEntryDto map(CartEntry cartEntry){
        CartEntryDto cartEntryDto = new CartEntryDto();
        cartEntryDto.setQuantity(String.valueOf(cartEntry.getSelectedQuantity()));
        cartEntryDto.setPricePerItem(String.valueOf(cartEntry.getProduct().getPrice()));
        cartEntryDto.setProductName(cartEntry.getProduct().getName());
        cartEntryDto.setPricePerSelection(String.valueOf(cartEntry.getProduct().getPrice()
                * cartEntry.getSelectedQuantity()));
        return cartEntryDto;
    }
}
