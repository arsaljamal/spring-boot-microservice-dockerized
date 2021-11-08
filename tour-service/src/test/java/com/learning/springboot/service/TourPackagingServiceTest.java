package com.learning.springboot.service;


import com.learning.springboot.domain.TourPackage;
import com.learning.springboot.repo.TourPackageRepository;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TourPackagingServiceTest {

    private static final String CODE = "kk";
    private static final String NAME = "karachi";

    @Mock
    private TourPackageRepository tourPackageRepositoryMock;

    @Mock
    private TourPackage tourPackageMock;

    @InjectMocks
    private TourPackageService tourPackageService;

    @Before
    public void setUpReturnValuesOfMockMethods() {
        when(tourPackageRepositoryMock.findById(CODE)).thenReturn(Optional.of(tourPackageMock));
    }

    @Test
    public void createTourPackage() {
        assertThat(tourPackageService.createTourPackage(CODE,NAME), is(tourPackageMock));
    }
}
