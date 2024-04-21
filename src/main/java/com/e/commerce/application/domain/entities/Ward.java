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
@Table(name = "Ward")
public class Ward {
    @Id
    @Column(name = "ward_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardId;
    @Column(name = "ward_name", nullable = false)
    private String wardName;

    @Column(name = "create_at")
    private LocalDate createAt = LocalDate.now();
    @Column(name = "update_at")
    private LocalDate updateAt = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private District district;
}
