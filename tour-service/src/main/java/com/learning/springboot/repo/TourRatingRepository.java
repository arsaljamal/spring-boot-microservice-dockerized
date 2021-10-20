package com.learning.springboot.repo;

import com.learning.springboot.domain.TourRating;
import com.learning.springboot.domain.TourRatingPk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Integer tourId);

    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}
