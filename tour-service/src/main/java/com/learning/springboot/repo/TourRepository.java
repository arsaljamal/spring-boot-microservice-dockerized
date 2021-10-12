package com.learning.springboot.repo;

import com.learning.springboot.domain.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {
}
