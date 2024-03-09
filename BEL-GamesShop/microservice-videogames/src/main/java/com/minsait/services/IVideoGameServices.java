package com.minsait.services;

import com.minsait.models.VideoGame;

import java.util.List;

public interface IVideoGameServices {

    List<VideoGame> findAll();
    VideoGame findById(Long id);
    VideoGame save(VideoGame videoGame);
    boolean deleteById(Long id);


}
