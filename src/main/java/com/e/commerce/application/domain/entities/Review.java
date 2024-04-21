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
@Table(name = "Reviews")
public class Review {
    @Id
    @Column(name = "review_id", updatable = false)
    private String reviewId;
    @Column(name = "user_id", updatable = false)
    private String userId;
    @Column(name = "product_id", updatable = false)
    private String productId;

    @Column(name = "is_like", nullable = false)
    private boolean isLike;
    @Column(name = "is_dislike", nullable = false)
    private boolean isDislike;

    @Column(name = "rate_star")
    private int rateStar;
    @Column(name = "rate_star_review")
    private String rateStarReview;

    @Column(name = "createAt")
    private LocalDate createdAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;

    public Review() {
        this.reviewId = RandomAnyString.generateCounterIncrement("Review-");
        this.isLike = false;
        this.isDislike = false;
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Review(String userId, String productId) {
        this.reviewId = RandomAnyString.generateCounterIncrement("Review-");
        this.userId = userId;
        this.productId = productId;
        this.isLike = false;
        this.isDislike = false;
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Review(String userId, String productId, boolean isLike, boolean isDislike, int rateStar, String rateStarReview) {
        this.reviewId = RandomAnyString.generateCounterIncrement("Review-");
        this.userId = userId;
        this.productId = productId;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.rateStar = rateStar;
        this.rateStarReview = rateStarReview;
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
