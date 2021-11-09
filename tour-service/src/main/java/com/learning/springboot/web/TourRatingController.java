package com.learning.springboot.web;


import com.learning.springboot.domain.Tour;
import com.learning.springboot.domain.TourRating;
import com.learning.springboot.repo.TourRepository;
import com.learning.springboot.service.TourRatingService;
import com.learning.springboot.repo.TourRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TourRatingController.class);
    TourRatingRepository tourRatingRepository;
    TourRepository tourRepository;
    TourRatingService tourRatingService;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository, TourRatingService tourRatingService) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
        this.tourRatingService = tourRatingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId,@RequestBody @Validated RatingDto ratingDto) {
        LOGGER.info("Path /tours/{}/ratings",tourId);
        tourRatingService.createNew(tourId, ratingDto.getCustomerId(),
                ratingDto.getScore(), ratingDto.getComment());
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        verifyTour(tourId);
        return tourRatingRepository.findByTourRatingPkTour(tourId).stream()
                .map(RatingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{customerId}")
    public RatingDto getRatingForTour(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId ) {
        verifyTour(tourId);
        Optional<TourRating> tourRating = tourRatingService.findTourRatingByTourIdAndCustomer(tourId, customerId);
        return new RatingDto(tourRating.get());
    }

    @GetMapping(path ="/average" )
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
        verifyTour(tourId);
        return Map.of("average", tourRatingRepository.findByTourRatingPkTour(tourId)
                .stream().mapToInt(TourRating::getScore).average().orElseThrow(() ->
                        new NoSuchElementException("Tour has no rating")));
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId,@RequestBody @Validated RatingDto ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());
        return new RatingDto(tourRatingRepository.save(rating));
    }

    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,@RequestBody @Validated RatingDto ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getScore() != null) {
            rating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            rating.setComment(ratingDto.getComment());
        }
        return new RatingDto(tourRatingRepository.save(rating));
    }

    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable(value = "tourId") int tourId,@PathVariable(value = "customerId") int customerId) {
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(rating);
    }

    public TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
        return tourRatingRepository.findByTourRatingPkTourAndTourRatingPkCustomerId(tourId, customerId).orElseThrow(()
                -> new NoSuchElementException("Tour-Rating pair for request("
                + tourId + " for customer" + customerId));
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("Tour does not exit " + tourId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
