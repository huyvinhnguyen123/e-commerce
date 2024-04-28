package com.e.commerce.application.web.response.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int page;
    private int size;
    private int totalPages;

    public BaseResponse(int page, int size, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
    }
}
