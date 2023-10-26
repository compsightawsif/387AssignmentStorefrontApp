package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/cart/products/{slug}")
public class CartModificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement logic to add a product to the user's cart
        // Check user authorization using AuthorizationUtil
        // Redirect or show a success/error message
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement logic to remove a product from the user's cart
        // Check user authorization using AuthorizationUtil
        // Redirect or show a success/error message
    }
}