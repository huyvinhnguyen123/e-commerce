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
@Table(name = "Types")
public class Type {
    @Id
    @Column(name = "type_id", updatable = false)
    private String typeId;
    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Type() {
        this.typeId = RandomAnyString.generateCounterIncrement("type-");
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Type(String name) {
        this.typeId = RandomAnyString.generateCounterIncrement("type-");
        this.typeName = name;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
