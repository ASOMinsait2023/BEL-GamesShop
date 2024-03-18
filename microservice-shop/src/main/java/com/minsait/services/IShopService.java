package com.minsait.services;
import com.minsait.models.Shop;
import com.minsait.models.Stock;

import java.util.List;
import java.util.Optional;
public interface IShopService {
    List<Shop> findAll();
    Shop findById(Long id);
    Shop save(Shop shop);
    boolean delleteById(Long idCuenta);
    List<Shop> queryForAddress(String address);
}