package com.minsait.microservicecategories.repositories;

import com.minsait.microservicecategories.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Categories, Long>{
    List<Categories> findAllByVideoGameId(Long videoGameId);

    @Query("SELECT c FROM Categories c JOIN c.platform p WHERE p.id = :platformId")
    List<Categories> findByPlatformId(@Param("platformId") Long platformId);
}
