package com.e.commerce.application.domain.entities.many;

import com.e.commerce.application.domain.entities.Image;
import com.e.commerce.application.domain.entities.User;
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
@Table(name = "User_Images")
public class UserImage {
    @Id
    @Column(name = "user_image_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userImageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Image image;
}