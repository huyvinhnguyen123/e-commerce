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
@Table(name = "Carts")
public class Cart {
    @Id
    @Column(name = "cart_id", updatable = false)
    private String cartId;
    @Column(name = "token")
    private String token;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "total_point", nullable = false)
    private Double totalPoint;
    @Column(name = "version_no", nullable = false)
    private Double versionNo;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Cart() {
        this.cartId = RandomAnyString.generateCounterIncrement("Cart-");
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Cart(String token) {
        this.cartId = RandomAnyString.generateCounterIncrement("Cart-");
        this.token = token;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Cart(Double totalPrice, Double totalPoint, Double versionNo) {
        this.cartId = RandomAnyString.generateCounterIncrement("Cart-");
        this.totalPrice = totalPrice;
        this.totalPoint = totalPoint;
        this.versionNo = versionNo;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}

