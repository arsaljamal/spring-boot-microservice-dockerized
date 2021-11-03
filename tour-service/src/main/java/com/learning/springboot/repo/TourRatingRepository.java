package com.learning.springboot.repo;

import com.learning.springboot.domain.TourRating;
import com.learning.springboot.domain.TourRatingPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByTourRatingPkTour(Integer tourId);

    //Optional<TourRating> findByTourRatingPkTourAndCustomerId(Integer tourId, Integer customerId);
}
