package com.learning.springboot.service;


import com.learning.springboot.domain.Tour;
import com.learning.springboot.domain.TourRating;
import com.learning.springboot.domain.TourRatingPk;
import com.learning.springboot.repo.TourRatingRepository;
import com.learning.springboot.repo.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TourRatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourRatingService.class);

    private TourRatingRepository tourRatingRepository;

    private TourRepository tourRepository;

    @Autowired
    public TourRatingService(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    public void createNew(int tourId, Integer customerId, Integer score, String comment) throws NoSuchElementException  {
        LOGGER.info("Creating rating for tour {} and customer {}", tourId, customerId);

        tourRatingRepository.save(new TourRating(new TourRatingPk(verifyTour(tourId), customerId), score, comment));
    }

    public Optional<TourRating> findTourRatingByTourIdAndCustomer(int tourId, Integer customerId) {
        return tourRatingRepository.findByTourRatingPkTourAndTourRatingPkCustomerId(tourId, customerId);
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("No such Tour found " + tourId));
    }
}
