package com.minsait.services;

import com.minsait.models.VideoGame;

import java.util.List;
import java.util.Optional;

public interface IVideoGameServices {

    List<VideoGame> findAll();
    VideoGame findById(Long id);
    VideoGame save(VideoGame videoGame);
    boolean deleteById(Long id);
    VideoGame findVideoGameById(Long id);


}
