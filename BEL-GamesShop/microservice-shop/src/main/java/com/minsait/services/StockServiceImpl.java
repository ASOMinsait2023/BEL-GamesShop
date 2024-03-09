package com.minsait.services;

import com.minsait.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class StockServiceImpl implements IStockService{
    @Autowired
    private IStockService stockService;
    @Override
    @Transactional(readOnly = true)
    public List<Stock> findAll() {
        return this.stockService.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Stock findById(Long id) {
        return stockService.findById(id);
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        stockService.save(stock);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Stock> stock = Optional.ofNullable(this.stockService.findById(id));
        if(stock.isPresent()){
            this.stockService.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
