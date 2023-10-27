package com.servlets;

import com.exceptions.ProductNotFoundException;
import com.model.Product;
import com.model.Storefront;
import com.model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/products/{slug}")
public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String slug = request.getParameter("slug");

        // Assuming you have an instance of the StorefrontService
        Storefront storefront = new Storefront();

        Product product = null;
        try {
            product = storefront.getProductBySlug(slug);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("product", product);
        request.getRequestDispatcher("/product_detail.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if the user is a staff member
        if ("secret".equals(request.getParameter("passcode"))) {
            session.setAttribute("userRole", Role.STAFF);
        }

        String sku = request.getParameter("sku");
        if (sku == null || sku.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid SKU");
            return;
        }
        Storefront storefront = new Storefront();
        Product product = null;
        try {
            product = storefront.getProduct(sku);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }
        // Retrieve form data for product updates
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String vendor = request.getParameter("vendor");
        String slug = request.getParameter("slug");
        double price = Double.parseDouble(request.getParameter("price"));


        // Update the product based on the form data
        try {
            storefront.updateProduct(sku, name, description, vendor, slug, price);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/products/" + slug);
    }

}