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
@Table(name = "Profiles")
public class Profile {
    @Id
    @Column(name = "profile_id", updatable = false)
    private String profileId;
    @Column(name = "date_join", nullable = false)
    private LocalDate dateJoin;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "total_point", nullable = false)
    private Double totalPoint;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Profile() {
        this.profileId = RandomAnyString.generateCounterIncrement("profile-");
        this.dateJoin = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
