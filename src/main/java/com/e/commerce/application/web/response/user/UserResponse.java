package com.e.commerce.application.web.response.user;

import com.e.commerce.application.domain.dtos.user.UserData;
import com.e.commerce.application.domain.dtos.user.UserImageData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private Iterable<UserData> userData;
    private Iterable<UserImageData> userImageData;
    private Long totalUsers;
    private Integer totalPages;
}
