package com.minsait.repositories;
import com.minsait.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IStockRepository extends JpaRepository<Stock, Long> {
    @Query(value="SELECT * FROM Stock s WHERE s.videogame = ?1")
    Optional<Stock> queryForVideoGame(Long id);
}
