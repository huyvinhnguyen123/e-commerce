package com.e.commerce.application.domain.entities.many;

import com.e.commerce.application.domain.entities.Cart;
import com.e.commerce.application.domain.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cart_Details")
public class CartDetail {
    @Id
    @Column(name = "cart_detail_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailId;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "point", nullable = false)
    private Double point;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "total_point", nullable = false)
    private Double totalPoint;
    @Column(name = "status_code")
    private Integer statusCode;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}