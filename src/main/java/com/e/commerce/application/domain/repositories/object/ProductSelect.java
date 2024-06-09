package com.e.commerce.application.domain.repositories.object;

import java.time.LocalDate;

public interface ProductSelect {
        /**
         * All these columns here have to write the same as column in table in db
         */
        String getProduct_id();
        String getBrand();
        String getSku();
        String getName();
        String getMaterial();
        String getRegion();
        Double getPrice();
        Double getPoint();
        String getDescription();
        LocalDate getDate_post();
        String getColor();
        Double getLength();
        Double getWidth();
        Double getHeight();
        Double getWeight();
}
