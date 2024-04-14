package com.e.commerce.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataInput {
    private String birthDate;
    private String name;
    private String email;
    private String password;
}
