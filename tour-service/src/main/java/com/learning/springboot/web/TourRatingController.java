package com.learning.springboot.web;


import com.learning.springboot.domain.Tour;
import com.learning.springboot.domain.TourRating;
import com.learning.springboot.domain.TourRatingPk;
import com.learning.springboot.repo.TourRatingRepository;
import com.learning.springboot.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

    TourRatingRepository tourRatingRepository;
    TourRepository tourRepository;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId,@RequestBody @Validated RatingDto ratingDto) {
        Tour tour = verifyTour(tourId);
        tourRatingRepository.save(new TourRating(new TourRatingPk(tour, ratingDto.getCustomerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        verifyTour(tourId);
        return tourRatingRepository.findByPkTourId(tourId).stream()
                .map(RatingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path ="/average" )
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
        verifyTour(tourId);
        return Map.of("average", tourRatingRepository.findByPkTourId(tourId)
                .stream().mapToInt(TourRating::getScore).average().orElseThrow(() ->
                        new NoSuchElementException("Tour has no rating")));
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException {
        return tourRepository.findById(tourId).orElseThrow(() ->
                new NoSuchElementException("Tour does not exit" + tourId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
