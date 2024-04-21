package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Cities")
public class City {
    @Id
    @Column(name = "city_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "create_at")
    private LocalDate createAt = LocalDate.now();
    @Column(name = "update_at")
    private LocalDate updateAt = LocalDate.now();
}
