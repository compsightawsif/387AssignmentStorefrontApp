package com.servlets;

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

@WebServlet("/products/{slug}")
public class ProductModificationServlet extends HttpServlet {

    Storefront storefront = new Storefront();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String passcode = request.getParameter("passcode");
        // Implement logic to modify a product
        // Check user authorization using AuthorizationUtil
        // Redirect or show a success/error message
        if (AuthorizationUtil.isStaffAuthorized(request, passcode)) {
            String sku = request.getParameter("sku");
            String slug = request.getParameter("slug");
            String updatedName = request.getParameter("name");
            String updatedDescription = request.getParameter("description");
            String updatedVendor = request.getParameter("vendor");
            double updatedPrice = Double.parseDouble(request.getParameter("price"));

            // Implement logic to modify a product in the Storefront
            // You can access request parameters to get updated product details
            // You may need to validate and sanitize the input data
//            Product updatedProduct = storefront.updateProduct(sku, updatedName, updatedDescription, updatedVendor, slug, updatedPrice);
            // Redirect or show a success/error message
//            response.sendRedirect("/products/" + updatedProduct.getSlug());

        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
        }
    }
}
