package com.minsait.services.clients;

import com.minsait.models.dto.VideoGameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-videogames", url = "http://localhost:7090/api/v1/videogames")
public interface IVideoGameClient {
    @GetMapping("{id}")
    VideoGameDTO findById(@PathVariable Long id);
}
