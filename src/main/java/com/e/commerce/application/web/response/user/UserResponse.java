package com.e.commerce.application.web.response.user;

import com.e.commerce.application.domain.dtos.user.UserData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private Iterable<UserData> userData;
    private Long totalUsers;
    private int page;
    private int size;
    private int totalPages;
}
