package com.e.commerce.application.web.response.login;

public class LoginMapper {
    private LoginMapper(){}
    public static LoginResponse mapToLogin(String token, String refreshToken) {
        return LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}

