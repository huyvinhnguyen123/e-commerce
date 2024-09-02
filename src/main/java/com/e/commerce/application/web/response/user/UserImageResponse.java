package com.e.commerce.application.web.response.user;

import com.e.commerce.application.domain.dtos.user.UserImageData;
import com.e.commerce.application.web.response.base.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserImageResponse {
    private Iterable<UserImageData> userImageData;
    private Long totalUsers;
    private int page;
    private int size;
    private int totalPages;
}
