package com.learning.springboot.repo;

import com.learning.springboot.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);

    Optional<TourPackage> findByCodeAndName(String code, String name);
}
