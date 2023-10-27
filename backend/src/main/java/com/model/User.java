package com.model;

public class User {
    private String username;
    private String password;
    private Role role;
    private Cart cart;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.cart = new Cart();
    }

    public void addProductToCart(Product product) {
        cart.addProduct(product);
    }

    public void removeProductFromCart(Product product) {
        cart.removeProduct(product);
    }
    // Getters and setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
