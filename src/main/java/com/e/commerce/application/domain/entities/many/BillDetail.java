package com.e.commerce.application.domain.entities.many;

import com.e.commerce.application.domain.entities.Bill;
import com.e.commerce.application.domain.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bill_Details")
public class BillDetail {
    @Id
    @Column(name = "bill_detail_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billDetailId;
    @Column(name = "datePaymentProduct", nullable = false)
    private LocalDate datePaymentProduct;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;
}
