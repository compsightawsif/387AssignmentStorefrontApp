package com.util;

import com.model.Role;
import jakarta.servlet.http.HttpSession;

public class AuthorizationUtil {
    public static boolean authenticateAsStaff(HttpSession session, String passcode) {
        if ("secret".equals(passcode)) {
            session.setAttribute("userRole", Role.STAFF);
            return true;
        }
        return false;
    }

    public static boolean isUserStaff(HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");
        return "staff".equals(userRole);
    }

}