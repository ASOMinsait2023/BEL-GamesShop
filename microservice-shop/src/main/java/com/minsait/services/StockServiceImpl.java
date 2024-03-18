package com.minsait.services;

import com.minsait.models.Stock;
import com.minsait.models.dto.StockDTOClient;
import com.minsait.models.dto.VideoGameDTO;
import com.minsait.repositories.IStockRepository;
import com.minsait.services.clients.IVideoGameClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements IStockService{
    @Autowired
    private IStockRepository stockRepository;
    @Autowired
        private IVideoGameClient videoGameClient;
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
    public List<StockDTOClient> getStockByShopId(Long shopId) {
        List<Stock> stocks = stockRepository.findByShopId(shopId);
        return stocks.stream()
                .map(this::mapStockToStockDTO)
                .collect(Collectors.toList());
    }

    private StockDTOClient mapStockToStockDTO(Stock stock) {
        VideoGameDTO videoGameDTO = videoGameClient.findById(stock.getVideogame());
        return StockDTOClient.builder()
                .id(stock.getId())
                .shop(stock.getShop())
                .videoGameDTO(videoGameDTO)
                .stock(stock.getStock())
                .build();
    }
}
