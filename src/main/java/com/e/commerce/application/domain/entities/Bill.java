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
@Table(name = "Bills")
public class Bill {
    @Id
    @Column(name = "bill_id", updatable = false)
    private String billId;
    @Column(name = "bill_name", nullable = false)
    private String billName;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "total_money", nullable = false)
    private Double totalMoney;
    @Column(name = "left_money", nullable = false)
    private Double leftMoney;

    @Column(name = "total_product_point", nullable = false)
    private Double totalProductPoint;
    @Column(name = "total_user_point", nullable = false)
    private Double totalUserPoint;
    @Column(name = "left_point", nullable = false)
    private Double leftPoint;

    @Column(name = "date_payment", nullable = false)
    private LocalDate datePayment;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @OneToOne
    @JoinColumn(name = "orderId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @OneToOne
    @JoinColumn(name = "currencyId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currency;

    public Bill() {
        this.billId = RandomAnyString.generateCounterIncrement("Bill-");
        this.billName = "Bill-" + LocalDate.now();
        this.datePayment = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Bill(Double totalPrice, Double totalMoney, Double leftMoney,
                Double totalProductPoint, Double totalUserPoint, Double leftPoint) {
        this.billId = RandomAnyString.generateCounterIncrement("Bill-");
        this.billName = "Bill-" + LocalDate.now();
        this.totalPrice = totalPrice;
        this.totalMoney = totalMoney;
        this.leftMoney = leftMoney;
        this.totalProductPoint = totalProductPoint;
        this.totalUserPoint = totalUserPoint;
        this.leftPoint = leftPoint;
        this.datePayment = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
