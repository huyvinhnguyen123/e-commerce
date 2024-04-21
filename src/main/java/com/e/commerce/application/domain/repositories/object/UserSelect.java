package com.e.commerce.application.domain.repositories.object;

import java.time.LocalDate;

public interface UserSelect {
    String getUserId();
    String getUsername();
    LocalDate getBirthdate();
    String getEmail();
    String getRoleName();
    LocalDate getDateCreate();
    String getImageName();
    String getImagePath();
}
