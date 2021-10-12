package com.learning.springboot.service;


import com.learning.springboot.domain.Difficulty;
import com.learning.springboot.domain.Region;
import com.learning.springboot.domain.Tour;
import com.learning.springboot.domain.TourPackage;
import com.learning.springboot.repo.TourPackageRepository;
import com.learning.springboot.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String tourPackageName, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
                new RuntimeException("TourPackage does not exists: " + tourPackageName));

        return tourRepository.save(new Tour(title, tourPackage, difficulty, region));
    }
    public long total() {
        return tourRepository.count();
    }
}
