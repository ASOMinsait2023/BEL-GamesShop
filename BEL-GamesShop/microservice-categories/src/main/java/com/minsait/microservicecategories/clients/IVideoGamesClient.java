package com.minsait.microservicecategories.clients;

import com.minsait.microservicecategories.models.dtos.VideoGameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "msvc-videogames", url = "http://msvc-videogames:7091/api/v1/videogames")
public interface IVideoGamesClient {
    @GetMapping("/search-by-category/{idCategory}")
    List<VideoGameDTO> findByIdCategory(Long idCategory);
}
