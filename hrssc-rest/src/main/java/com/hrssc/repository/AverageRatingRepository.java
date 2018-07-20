package com.hrssc.repository;

import com.hrssc.entities.AverageRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageRatingRepository extends JpaRepository<AverageRating, Integer> {
    AverageRating findByHumanResourceId(int resourceId);
}
