package com.servlets;

import com.model.Cart;
import com.model.Product;
import com.model.Storefront;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/cart/products/{slug}")
public class CartModificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = new Cart();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // If the user doesn't exist in the session, create one
            user = new User(user.getUsername(), user.getPassword(), user.getRole());
            session.setAttribute("user", user);
        }

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement logic to remove a product from the user's cart
        // Check user authorization using AuthorizationUtil
        // Redirect or show a success/error message
        // Get the user from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
            return;
        }
        String sku = request.getParameter("sku");
        Product productToRemove = new Product(sku, null, null, null, null, 0.0);

        if (sku == null || sku.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid SKU");
            return;
        }
        // Remove the product from the user's cart
        user.removeProductFromCart(productToRemove);
    }
}