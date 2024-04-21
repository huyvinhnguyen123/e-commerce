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
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "order_id", updatable = false)
    private String orderId;
    @Column(name = "display_id", nullable = false)
    private Integer displayId;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "total_point", nullable = false)
    private Double totalPoint;
    @Column(name = "status_code")
    private Integer statusCode;
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;
    @Column(name = "is_payment", nullable = false)
    private Boolean isPayment;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Order() {
        this.orderId = RandomAnyString.generateCounterIncrement("Order-");
        this.isPayment = false;
        this.orderDate = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Order(Integer displayId, Double totalPrice, Double totalPoint, Integer statusCode) {
        this.orderId = RandomAnyString.generateCounterIncrement("Order-");
        this.isPayment = false;
        this.displayId = displayId;
        this.totalPrice = totalPrice;
        this.totalPoint = totalPoint;
        this.statusCode = statusCode;
        this.orderDate = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}