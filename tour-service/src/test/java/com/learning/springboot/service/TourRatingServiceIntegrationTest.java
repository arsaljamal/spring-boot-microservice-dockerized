package com.learning.springboot.service;

import com.learning.springboot.domain.TourPackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TourRatingServiceIntegrationTest {

    private static final String CODE = "kk";
    private static final String NAME = "karachi";

    @Autowired
    private TourPackageService tourPackageService;

    @Test
    public void createNew() {
        tourPackageService.createTourPackage(CODE, NAME);

        TourPackage tourPackage = tourPackageService.verifyTourPackage(CODE,NAME);
        assertThat(tourPackage.getCode(),is(CODE));
        assertThat(tourPackage.getName(),is(NAME));
    }
}
