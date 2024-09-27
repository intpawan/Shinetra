package com.contact.contact.controller;

import com.contact.contact.dto.CartDto;
import com.contact.contact.dto.ProductDto;
import com.contact.contact.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
@CrossOrigin(origins = "http://localhost:63342")
public class CartController {

    @Autowired
    private CartService cartService;

    // Endpoint to add a product to the cart
    @PostMapping("/{cartId}/add")
    public CartDto addToCart(@PathVariable Integer cartId, @RequestBody ProductDto productDto) {
        return cartService.addToCart(cartId, productDto);
    }

    // Endpoint to get the cart details by cart ID
    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable Integer cartId) {
        return cartService.getCart(cartId);
    }

    // New endpoint to create a new cart
    @PostMapping("/create")
    public CartDto createCart() {
        return cartService.createCart();
    }

    @PostMapping("/{cartId}/checkout")
    public ResponseEntity<CartDto> checkout(@PathVariable Integer cartId) {
        // Clear the cart using the service method
        CartDto clearedCart = cartService.clearCart(cartId);

        // Return the cleared cart and a success message
        return ResponseEntity.ok(clearedCart);
    }



}
