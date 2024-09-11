package com.adrian.my_shop.controller;

import com.adrian.my_shop.dto.*;
import com.adrian.my_shop.service.CartService;
import com.adrian.my_shop.service.OrderService;
import com.adrian.my_shop.service.ProductService;
import com.adrian.my_shop.service.UserService;
import com.adrian.my_shop.validator.ProductDtoValidator;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDtoValidator productDtoValidator;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String getHomePage (Model model){
        List<ProductDto> productDtoList = productService.getAllProductDtoList();
        model.addAttribute("productDtoList", productDtoList);
        System.out.println(productDtoList);
        return "home";
    }

    @GetMapping("/add-product")
    public String getAddProdcutPage (Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String postAddProduct(@ModelAttribute ProductDto productDto, BindingResult bindingResult,
                                 @RequestParam ("imageFile") MultipartFile imageFile) throws IOException {
        System.out.println("S-a apelat metoda postAddProduct " + productDto);
        System.out.println(imageFile.getBytes());
        productDtoValidator.validate(productDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "addProduct";
        }
        productService.addProduct(productDto, imageFile);
        return "redirect:/add-product";
    }

    @GetMapping("/product/{productId}")
    public String getViewProduct (@PathVariable(value = "productId") String productId, Model model) {
        System.out.println("Am dat click pe produsul cu Id-ul " + productId);
        Optional<ProductDto> optionalProductDto = productService.getProductDtoByProductId(productId);
        if (optionalProductDto.isEmpty()){
            return "error";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("productDto", productDto);
        SelectionDto selectionDto = new SelectionDto();
        model.addAttribute("selectionDto", selectionDto);
        return "viewProduct";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDto userDto){
        System.out.println(userDto);
        userService.register(userDto);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/product/{product_id}")
    public String postAddToCart(@PathVariable(value = "product_id") String product_id, @ModelAttribute SelectionDto selectionDto,
                                Authentication authentication){
        cartService.addToCart(authentication.getName(), selectionDto, product_id);
        System.out.println("Vreau sa adaug " + selectionDto.getQuantity() + " pentru prosului cu id-ul: " + product_id);
        System.out.println("Userul logat: " + authentication.getName());
        return "redirect:/product/" + product_id;
    }

    @GetMapping("/viewCart")
    public String getViewCart(Authentication authentication, Model model){
        CartDto cartDto = cartService.getCartDtoFor(authentication.getName());
        System.out.println(cartDto);
        model.addAttribute("cartDto", cartDto);
        OrderDto orderDto = new OrderDto();
        model.addAttribute("orderDto", orderDto);
        return "viewCart";
    }

    @PostMapping("/finalize-order")
    public String postFinalizeOrder(@ModelAttribute OrderDto orderDto, Authentication authentication){
        System.out.println(orderDto);
        orderService.finalizeOrder(orderDto, authentication.getName());
        return "orderFinalized";
    }
}
