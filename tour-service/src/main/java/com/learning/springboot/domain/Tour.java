package com.learning.springboot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tour {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @ManyToOne
    private TourPackage tourPackage;

    @Column
    @Enumerated
    private Difficulty difficulty;

    @Column
    @Enumerated
    private Region region;

    public Tour(Integer id, String title, TourPackage tourPackage, Difficulty difficulty, Region region) {
        this.id = id;
        this.title = title;
        this.tourPackage = tourPackage;
        this.difficulty = difficulty;
        this.region = region;
    }

    protected Tour() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tourPackage=" + tourPackage +
                ", difficulty=" + difficulty +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(id, tour.id) && Objects.equals(title, tour.title) && Objects.equals(tourPackage, tour.tourPackage) && difficulty == tour.difficulty && region == tour.region;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tourPackage, difficulty, region);
    }
}
