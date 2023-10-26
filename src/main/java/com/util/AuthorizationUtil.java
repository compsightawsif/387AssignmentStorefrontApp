package com.util;

import jakarta.servlet.http.HttpServletRequest;

public class AuthorizationUtil {
    public static boolean isUserAuthorized(HttpServletRequest request) {
        // Implement logic to check user authorization based on session or other mechanisms
        return true;
    }

    public static boolean isStaffAuthorized(HttpServletRequest request, String passcode) {
        // Implement logic to check staff authorization based on session or other mechanisms
        return "secret".equals(passcode);
    }
}