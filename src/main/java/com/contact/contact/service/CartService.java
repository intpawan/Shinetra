package com.contact.contact.service;

import com.contact.contact.dto.CartDto;
import com.contact.contact.dto.ProductDto;
import com.contact.contact.model.Cart;
import com.contact.contact.model.Product;
import com.contact.contact.repository.CartRepository;
import com.contact.contact.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add product to the cart
    public CartDto addToCart(Integer cartId, ProductDto productDto) {
        // Find the cart by ID, and if not found, return null or handle the case
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        // Convert ProductDto to Product and save it in the database
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        // Save the product in the database
        Product savedProduct = productRepository.save(product);

        // Add the saved product to the cart
        cart.getProducts().add(savedProduct);
        cart.setTotalPrice(cart.getTotalPrice() + savedProduct.getPrice());

        // Save the updated cart after adding the product
        Cart updatedCart = cartRepository.save(cart);

        // Return the updated CartDto
        return new CartDto(
                updatedCart.getId(),
                updatedCart.getProducts().stream()
                        .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                        .collect(Collectors.toList()),
                updatedCart.getTotalPrice()
        );
    }

    // Fetch cart details
    public CartDto getCart(Integer cartId) {
        // Find cart by ID, or create a new one if not found
        Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setTotalPrice(0);
            return cartRepository.save(newCart); // Save new cart to database
        });

        // Return cart details
        return new CartDto(
                cart.getId(),
                cart.getProducts().stream()
                        .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                        .collect(Collectors.toList()),
                cart.getTotalPrice()
        );
    }


    //  method to create a new cart
    public CartDto createCart() {
        // Create a new cart with an initial total price of 0
        Cart newCart = new Cart();
        newCart.setTotalPrice(0);
        Cart savedCart = cartRepository.save(newCart);

        // Return the new cart as a CartDto
        return new CartDto(savedCart.getId(), new ArrayList<>(), savedCart.getTotalPrice());
    }


    public CartDto clearCart(Integer cartId) {
        // Fetch the cart by ID
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        // Clear products and reset total price
        cart.getProducts().clear();
        cart.setTotalPrice(0);

        // Save the updated cart
        Cart updatedCart = cartRepository.save(cart);

        // Return the updated cart with cleared products and reset total price
        return new CartDto(
                updatedCart.getId(),
                new ArrayList<>(),  // Empty list of products
                updatedCart.getTotalPrice()
        );
    }



}
