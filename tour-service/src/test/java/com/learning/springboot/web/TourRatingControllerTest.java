package com.learning.springboot.web;

import com.learning.springboot.domain.Tour;
import com.learning.springboot.domain.TourRating;
import com.learning.springboot.domain.TourRatingPk;
import com.learning.springboot.repo.TourRepository;
import com.learning.springboot.service.TourRatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TourRatingControllerTest {

    private static final int TOUR_ID = 999;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";
    private static final String TOUR_RATING_URL = "/tours/"+TOUR_ID+"/ratings";

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    TourRatingService tourRatingServiceMock;

    @MockBean
    TourRepository tourRepositoryMock;

    @Mock
    TourRating tourRatingMock;

    @Mock
    Tour tourMock;

    private RatingDto ratingDto = new RatingDto(SCORE, COMMENT, CUSTOMER_ID);

    @Before
    public void setupReturnValuesOfMockMethods() {
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getTourRatingPk()).thenReturn(new TourRatingPk(tourMock, CUSTOMER_ID));
        when(tourMock.getId()).thenReturn(TOUR_ID);
    }

    @Test
    public void createTourRating() {
        testRestTemplate.postForEntity(TOUR_RATING_URL, ratingDto, Void.class);

        verify(this.tourRatingServiceMock).createNew(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

    @Test
    public void getTourRating() {
        when(tourRepositoryMock.findById(TOUR_ID)).thenReturn(Optional.of(tourMock));
        when(tourRatingServiceMock.findTourRatingByTourIdAndCustomer(TOUR_ID,CUSTOMER_ID))
                .thenReturn(Optional.of(tourRatingMock));

        ResponseEntity<String> response = testRestTemplate.
                getForEntity(TOUR_RATING_URL+"/"+CUSTOMER_ID, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}
