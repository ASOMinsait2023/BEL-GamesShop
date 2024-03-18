package com.minsait.repositories;

import com.minsait.models.Shop;

import java.util.List;
import java.util.Optional;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IShopRepository extends JpaRepository<Shop, Long> {
    //@Query(value = "SELECT * FROM Shops s WHERE s.address SOUNDS LIKE %?1%", nativeQuery = true)
    //Optional<Shop> queryForAddress(String address);
    @Query(value = "SELECT * FROM Shops s WHERE SOUNDEX(s.address) = SOUNDEX(:address)", nativeQuery = true)
    List<Shop> queryForAddress(@Param("address") String address);
}

