package com.minsait.services;
import com.minsait.models.Shop;
import com.minsait.models.Stock;

import java.util.List;
import java.util.Optional;
public interface IShopService {
    List<Shop> findAll();
    Shop findById(Long id);
    void save(Shop shop);
    boolean delleteById(Long idCuenta);
    Optional<Shop> queryForAddress(String address);
    Optional<Stock> queryStockShop(Long id);
}