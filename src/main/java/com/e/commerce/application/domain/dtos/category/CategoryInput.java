package com.e.commerce.application.domain.dtos.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInput {
//    @NotNull(message = "{cate.cate_name.require}")
//    @NotEmpty(message = "{cate.cate_name.require}")
    private String name;
}
