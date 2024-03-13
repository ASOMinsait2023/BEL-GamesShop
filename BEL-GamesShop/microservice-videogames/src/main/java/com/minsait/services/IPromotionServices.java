package com.minsait.services;

import com.minsait.models.Promotion;

import java.util.List;

public interface IPromotionServices {

    List<Promotion> findAll();
    Promotion findById(Long id);
    Promotion save(Promotion promotion);
    boolean deleteById(Long id);

    List<Promotion> getPromotionSearchVideogameById(Long videoGameId);

}
