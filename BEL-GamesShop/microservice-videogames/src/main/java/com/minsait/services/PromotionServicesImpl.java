package com.minsait.services;

import com.minsait.models.Promotion;
import com.minsait.repositories.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionServicesImpl implements IPromotionServices{

    @Autowired
    IPromotionRepository promotionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> findAll() {
        return (List<Promotion>) promotionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        var promotionDelete = promotionRepository.findById(id);
        if (promotionDelete.isPresent()){
            promotionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
