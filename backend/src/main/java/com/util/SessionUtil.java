package com.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static String getUserRole(HttpSession session) {
        return (String) session.getAttribute("userRole");
    }
}
