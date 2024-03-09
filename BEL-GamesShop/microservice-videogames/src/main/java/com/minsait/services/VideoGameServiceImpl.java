package com.minsait.services;

import com.minsait.models.VideoGame;
import com.minsait.repositories.IVideoGameRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoGameServiceImpl implements IVideoGameServices{

    @Autowired
    private IVideoGameRespository videoGameRespository;

    @Override
    @Transactional(readOnly = true)
    public List<VideoGame> findAll() {
        return (List<VideoGame>) videoGameRespository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VideoGame findById(Long id) {
        return videoGameRespository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public VideoGame save(VideoGame videoGame) {
         return videoGameRespository.save(videoGame);

    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
       var videoGameDelete = videoGameRespository.findById(id);
       if (videoGameDelete.isPresent()){
           videoGameRespository.deleteById(id);
            return true;
       }
       return false;
    }

}
