package com.minsait.services;

import com.minsait.models.Stock;

import java.util.List;

public interface IStockService {
    List<Stock> findAll();
    Stock findById(Long id);
    void save(Stock stock);
    boolean deleteById(Long id);

}
