package com.e.commerce.application.domain.repositories.object;

import java.time.LocalDate;

public interface UserSelect {
    /**
     * All these columns here have to write the same as column in table in db
     */
    String getUser_id();
    String getUsername();
    LocalDate getBirthdate();
    String getEmail();
    String getRole_name();
    LocalDate getDate_create();
    String getImageName();
    String getImagePath();
}
