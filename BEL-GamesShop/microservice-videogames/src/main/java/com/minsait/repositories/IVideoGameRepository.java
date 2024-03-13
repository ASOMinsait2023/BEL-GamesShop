package com.minsait.repositories;



import com.minsait.models.VideoGame;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoGameRepository extends JpaRepository<VideoGame, Long> {
    @Query(value = "SELECT v.id, v.name, v.description, v.release_date, v.price * (1 - (p.percentage / 100)) AS price " +
            "FROM videogames v " +
            "LEFT JOIN promotion p ON v.id = p.videogame " +
            "WHERE v.id = :videoGameId AND p.start_date <= CURDATE() AND p.end_date >= CURDATE()", nativeQuery = true)
    List<VideoGame> findVideoGameWithDiscount(@Param("videoGameId") Long videoGameId);



}
