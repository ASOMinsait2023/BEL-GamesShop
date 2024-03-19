package com.minsait.client;

import com.minsait.models.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-categories", url = "http://localhost:7090/api/v1/categories")
public interface ICategoriesClient {

    @GetMapping("/search-by-video-game/{idVideoGame}")
    List<CategoryDTO> findByVideoGameId(@PathVariable Long idVideoGame);

}
