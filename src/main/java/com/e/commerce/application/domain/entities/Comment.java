package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.common.RandomAnyString;
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
@Table(name = "Comments")
public class Comment {
    @Id
    @Column(name = "comment_id", updatable = false)
    private String commentId = RandomAnyString.generateCounterIncrement("comment-");
    @Column(name = "comment_review")
    private String commentReview;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

    @Column(name = "create_at")
    private LocalDate createAt = LocalDate.now();
    @Column(name = "update_at")
    private LocalDate updateAt = LocalDate.now();
}
