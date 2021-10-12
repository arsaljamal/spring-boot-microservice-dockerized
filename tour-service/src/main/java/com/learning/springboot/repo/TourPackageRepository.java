package com.learning.springboot.repo;

import com.learning.springboot.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
}
