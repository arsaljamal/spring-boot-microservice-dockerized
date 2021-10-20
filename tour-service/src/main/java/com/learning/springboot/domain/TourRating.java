package com.learning.springboot.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class TourRating {

    @EmbeddedId
    private TourRatingPk tourRatingPk;

    @Column
    private Integer score;

    @Column
    private String comment;

    public TourRating(TourRatingPk tourRatingPk, Integer score, String comment) {
        this.tourRatingPk = tourRatingPk;
        this.score = score;
        this.comment = comment;
    }

    public TourRating() {

    }

    public TourRatingPk getTourRatingPk() {
        return tourRatingPk;
    }

    public void setTourRatingPk(TourRatingPk tourRatingPk) {
        this.tourRatingPk = tourRatingPk;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRating that = (TourRating) o;
        return Objects.equals(tourRatingPk, that.tourRatingPk) && Objects.equals(score, that.score) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourRatingPk, score, comment);
    }

    @Override
    public String toString() {
        return "TourRating{" +
                "tourRatingPk=" + tourRatingPk +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
