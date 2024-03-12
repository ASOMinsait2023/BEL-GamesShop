package com.minsait.repositories;

import com.minsait.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByVideogameId(Long videogameId);
  }
