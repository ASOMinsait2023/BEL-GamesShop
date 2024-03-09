package com.minsait.services;

import com.minsait.models.Shop;
import com.minsait.models.Stock;
import com.minsait.repositories.IShopRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopRepository shopRepository;
    @Transactional(readOnly = true)
    public List<Shop> findAll() {
        return this.shopRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Shop findById(Long id) {
        return (Shop)this.shopRepository.findById(id).orElseThrow();
    }
    @Transactional
    public void save(Shop shop) {
        this.shopRepository.save(shop);
    }
    @Transactional
    public boolean delleteById(Long idShop) {
        Optional<Shop> shop = this.shopRepository.findById(idShop);
        if (shop.isPresent()) {
            this.shopRepository.deleteById(idShop);
            return true;
        } else {
            return false;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Shop> queryForAddress(String address) {
        return Optional.of((Shop)this.shopRepository.queryForAddress(address).orElseThrow());
    }

    @Override
    public Optional<Stock> queryStockShop(Long id) {
        return Optional.empty();
    }
}

