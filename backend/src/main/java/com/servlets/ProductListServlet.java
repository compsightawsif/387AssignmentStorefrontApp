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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Storefront storefront = new Storefront();
        List<Product> products = storefront.getAllProducts();

        request.setAttribute("products", products);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }
}
