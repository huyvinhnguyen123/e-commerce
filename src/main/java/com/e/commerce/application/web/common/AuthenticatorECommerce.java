package com.e.commerce.application.web.common;

import com.e.commerce.application.domain.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticatorECommerce {
    private static final Map<String, String> hashMapping = new HashMap<>();
    static {
        // role
        hashMapping.put("RSECM", "ROLE_SYSTEM");
        hashMapping.put("RAECM", "ROLE_ADMIN");
        hashMapping.put("RUECM", "ROLE_USER");
    }

    /**
     * extract role from role code
     * @param roleCode - input roleCode
     * @return - actual role
     */
    public String extractRole(String roleCode) {
        return hashMapping.get(roleCode);
    }

//    public String extractValue(Authentication authentication, String role) {
//        User userLogin = new User();
//        if (authentication != null && authentication.isAuthenticated()) {
//            userLogin = (User) authentication.getPrincipal();
//        }
//
//        return userLogin.getRole().getRoleName();
//    }
}
