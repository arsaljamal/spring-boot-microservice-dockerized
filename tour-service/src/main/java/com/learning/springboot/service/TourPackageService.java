package com.learning.springboot.service;


import com.learning.springboot.domain.TourPackage;
import com.learning.springboot.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourPackageService {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    public Iterable<TourPackage> lookUp() { return tourPackageRepository.findAll();}

    public long total() {return tourPackageRepository.count();}

    public TourPackage verifyTourPackage(String code, String name) throws NoSuchElementException {
        return tourPackageRepository.findByCodeAndName(code, name).orElseThrow(() ->
                new NoSuchElementException("No Such TourPackage found!"));
    }
}
