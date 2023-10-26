package com.servlets;

import com.model.Product;
import com.util.*;
import com.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement logic to fetch and display a list of all products
        // Check user authorization using AuthorizationUtil
        // Forward to productList.jsp
//        if(AuthorizationUtil.isUserAuthorized(request)) {
//            List<Product> products = Storefront.getAllProducts();
//            request.setAttribute("products", products);
//            request.getRequestDispatcher("/productList.jsp").forward(request, response);
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
//        }
    }
}
