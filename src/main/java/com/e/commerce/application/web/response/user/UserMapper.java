package com.e.commerce.application.web.response.user;

import com.e.commerce.application.domain.dtos.user.UserData;
import com.e.commerce.application.domain.dtos.user.UserImageData;

public class UserMapper {
    private UserMapper() {}

    public static UserResponse mapToUser(Iterable<UserData> userData, Long totalUsers, Integer totalPages) {
        return UserResponse.builder()
                .userData(userData)
                .totalUsers(totalUsers)
                .totalPages(totalPages)
                .build();
    }

    public static UserResponse mapToUserImage(Iterable<UserImageData> userData, Long totalUsers, Integer totalPages) {
        return UserResponse.builder()
                .userImageData(userData)
                .totalUsers(totalUsers)
                .totalPages(totalPages)
                .build();
    }
}
