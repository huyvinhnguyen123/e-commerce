package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Districts")
public class District {
    @Id
    @Column(name = "district_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
    @Column(name = "district_name", nullable = false)
    private String districtName;

    @Column(name = "create_at")
    private LocalDate createAt = LocalDate.now();
    @Column(name = "update_at")
    private LocalDate updateAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;
}
