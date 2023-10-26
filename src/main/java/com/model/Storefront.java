package com.model;

import com.exceptions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storefront {
    private List<Product> productCatalog = new ArrayList<>();
    private Map<String, Cart> userCarts = new HashMap<>();

    public Product createProduct(String sku, String name) {
        // Create a new product and add it to the catalog
        Product product = new Product(sku, name, null, null, null, 0.0);
        productCatalog.add(product);
        return product;
    }

    public void updateProduct(String sku, String name, String description, String vendor, String slug, double price) throws ProductNotFoundException {
        // Find and update the product with the given SKU
        for (Product product : productCatalog) {
            if (product.getSku().equals(sku)) {
                // Update product details only if provided values are not null
                if (name != null) {
                    product.setName(name);
                }
                if (description != null) {
                    product.setDescription(description);
                }
                if (vendor != null) {
                    product.setVendor(vendor);
                }
                if (slug != null) {
                    product.setSlug(slug);
                }
                if (price >= 0) {
                    product.setPrice(price);
                }
                return;
            }
        }
        throw new ProductNotFoundException("Product with SKU " + sku + " not found.");
    }


    public Product getProduct(String sku) throws ProductNotFoundException {
        // Find and return the product with the given SKU
        for (Product product : productCatalog) {
            if (product.getSku().equals(sku)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product with SKU " + sku + " not found.");
    }

    public Product getProductBySlug(String slug) throws ProductNotFoundException {
        for (Product product : productCatalog) {
            if (product.getSlug().equals(slug)) {
                return product;
            }
        }
        throw new ProductNotFoundException("Product with slug " + slug + " not found.");
    }

    public List<Product> getCart(String user) {
        userCarts.putIfAbsent(user, new Cart());
        return userCarts.get(user).getProducts();
    }

    public void addProductToCart(String user, String sku) throws ProductNotFoundException {
        Product productToAdd = getProduct(sku);
        Cart cart = userCarts.get(user);
        if (cart == null) {
            cart = new Cart();
            userCarts.put(user, cart);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void removeProductFromCart(String user, String sku) throws ProductNotFoundException, CartNotFoundException {
        Cart cart = userCarts.get(user);
        if (cart == null) {
            throw new CartNotFoundException("There is no cart");
        }

        Product productToRemove = getProduct(sku);
        if (productToRemove == null) {
            throw new ProductNotFoundException("There is no product to remove");
        }
        else {
            cart.removeProduct(productToRemove);
        }
    }

    public void downloadProductCatalog(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=product_catalog.json");
        response.setContentType("application/json");

        // Use Jackson ObjectMapper to write the JSON to the response output
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        objectMapper.writeValue(writer, productCatalog);
    }
    // Error handling and other methods as needed
}


