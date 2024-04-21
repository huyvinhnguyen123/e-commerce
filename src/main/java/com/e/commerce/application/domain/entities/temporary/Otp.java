package com.e.commerce.application.domain.entities.temporary;

import com.e.commerce.application.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Otps")
public class Otp {
    @Id
    @Column(name = "otp_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpId;
    @Column(name = "code")
    private String code;
    @Column(name = "time_created")
    private LocalTime timeCreated;
    @Column(name = "time_deleted")
    private LocalTime timeDeleted;
    @Column(name = "time_specify")
    private Integer timeSpecify;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Otp() {
        this.timeCreated = LocalTime.now();
        this.timeDeleted = LocalTime.now().plusMinutes(5);
    }
}
