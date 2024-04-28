package com.e.commerce.application.web.response.user;

import com.e.commerce.application.domain.dtos.user.UserData;
import com.e.commerce.application.domain.dtos.user.UserImageData;
import com.e.commerce.application.web.response.base.BaseResponse;

public class UserMapper {
    private UserMapper() {}

    public static UserResponse mapToUser(Iterable<UserData> userData, Long totalUsers, BaseResponse baseResponse) {
        return UserResponse.builder()
                .userData(userData)
                .totalUsers(totalUsers)
                .page(baseResponse.getPage())
                .size(baseResponse.getSize())
                .totalPages(baseResponse.getTotalPages())
                .build();
    }

    public static UserImageResponse mapToUserImage(Iterable<UserImageData> userImageData, Long totalUsers,BaseResponse baseResponse) {
        return UserImageResponse.builder()
                .userImageData(userImageData)
                .totalUsers(totalUsers)
                .page(baseResponse.getPage())
                .size(baseResponse.getSize())
                .totalPages(baseResponse.getTotalPages())
                .build();
    }
}
