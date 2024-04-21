package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.common.RandomAnyString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Currencies")
public class Currency {
    @Id
    @Column(name = "currency_id", updatable = false)
    private String currencyId;
    @Column(name = "currency_name", unique = true, nullable = false)
    private String currencyName;
    @Column(name = "currency_code", unique = true, nullable = false)
    private String currencyCode;
    @Column(name = "symbol", nullable = false)
    private String symbol;
    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;
    @Column(name = "default_currency", nullable = false)
    private Double defaultCurrency;
    @Column(name = "is_default_currency", nullable = false)
    private Boolean isDefaultCurrency;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Currency() {
        this.currencyId = RandomAnyString.generateCounterIncrement("Currency-");
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}