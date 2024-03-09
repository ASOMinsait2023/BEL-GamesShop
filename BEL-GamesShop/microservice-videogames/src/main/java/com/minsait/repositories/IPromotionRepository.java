package com.minsait.repositories;

import com.minsait.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {

  }
