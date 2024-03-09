package com.minsait.repositories;

import com.minsait.models.Shop;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IShopRepository extends JpaRepository<Shop, Long> {
    @Query(value = "SELECT * FROM Shop s WHERE s.address SOUNDS LIKE '%?1%'", nativeQuery = true)
    Optional<Shop> queryForAddress(String address);
}

