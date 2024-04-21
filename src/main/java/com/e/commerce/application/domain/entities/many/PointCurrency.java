package com.e.commerce.application.domain.entities.many;

import com.e.commerce.application.domain.entities.Currency;
import com.e.commerce.application.domain.entities.Point;
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
@Table(name = "Point_Currencies")
public class PointCurrency {
    @Id
    @Column(name = "point_currency_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointCurrencyId;

    @ManyToOne
    @JoinColumn(name = "point_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Point point;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currency;
}
