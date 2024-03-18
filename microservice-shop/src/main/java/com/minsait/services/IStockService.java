package com.minsait.services;

import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTOClient;

import java.util.List;

public interface IStockService {
    List<Stock> findAll();
    Stock findById(Long id);
    Stock save(Stock stock);
    boolean deleteById(Long id);
    List<StockDTOClient> getStockByShopId(Long shopId);
}
