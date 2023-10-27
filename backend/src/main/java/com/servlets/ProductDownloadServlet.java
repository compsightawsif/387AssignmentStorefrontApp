package com.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Product;
import com.model.Storefront;
import com.util.AuthorizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/products/download")
public class ProductDownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if the user is staff and authenticate if needed
        if (AuthorizationUtil.authenticateAsStaff(session, request.getParameter("passcode"))) {
            // Successfully authenticated as staff
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. Only staff members can download the catalog.");
            return;
        }

        // Generate the product catalog in JSON format
        Storefront Storefront = new Storefront();
        List<Product> productCatalog = Storefront.getAllProducts();
        String jsonCatalog = generateJsonCatalog(productCatalog);

        // Set response headers for file download
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=product_catalog.json");

        // Write the JSON catalog to the response output stream
        try (PrintWriter out = response.getWriter()) {
            out.write(jsonCatalog);
        }
    }

    private String generateJsonCatalog(List<Product> productCatalog) {
        // Use Jackson to convert the productCatalog list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(productCatalog);
        } catch (JsonProcessingException e) {
            // Handle JSON conversion error
            e.printStackTrace();
            return "{}";
        }
    }
}
