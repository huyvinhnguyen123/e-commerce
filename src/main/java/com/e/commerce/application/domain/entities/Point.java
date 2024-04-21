package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.common.RandomAnyString;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Points")
public class Point {
    @Id
    @Column(name = "point_id", updatable = false)
    private String pointId;
    @Column(name = "point_exchange", nullable = false)
    private Double pointExchange;
    @Column(name = "decimal_place", nullable = false)
    private Double decimalPlace;
    @Column(name = "exchange_value", nullable = false)
    private Double exchangeValue;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;

    public Point() {
        this.pointId = RandomAnyString.generateCounterIncrement("point-");
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Point(Double pointExchange, Double decimalPlace, Double exchangeValue) {
        this.pointId = RandomAnyString.generateCounterIncrement("point-");
        this.pointExchange = pointExchange;
        this.decimalPlace = decimalPlace;
        this.exchangeValue = exchangeValue;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}