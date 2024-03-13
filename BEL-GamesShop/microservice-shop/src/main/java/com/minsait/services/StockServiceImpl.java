package com.minsait.services;

import com.minsait.models.Stock;
import com.minsait.repositories.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements IStockService{
    @Autowired
    private IStockRepository stockRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Stock> findAll() {
        return this.stockRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Stock findById(Long id) {
        return stockRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if(stock.isPresent()){
            this.stockRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<Stock> getStockByShopId(Long shopId) {
        return stockRepository.findByShopId(shopId);
    }
}
