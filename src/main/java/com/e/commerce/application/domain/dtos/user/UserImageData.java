package com.e.commerce.application.domain.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserImageData {
    private String id;
    private LocalDate birthDate;
    private String name;
    private String email;
    private String role;
    private LocalDate dateCreate;
    private String imageName;
    private String imagePath;
}
