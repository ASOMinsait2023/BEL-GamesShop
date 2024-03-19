package com.minsait.services;

import com.minsait.client.ICategoriesClient;
import com.minsait.models.VideoGame;
import com.minsait.models.dto.CategoryByVideoGameDTO;
import com.minsait.repositories.IVideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@Service
public class VideoGameServiceImpl implements IVideoGameServices{

     @Autowired
    IVideoGameRepository videoGameRepository;
     @Autowired
    ICategoriesClient iCategoriesClient;

    @Override
    @Transactional(readOnly = true)
    public List<VideoGame> findAll() {
        return (List<VideoGame>) videoGameRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VideoGame findById(Long id) {
        return videoGameRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public VideoGame save(VideoGame videoGame) {
         return videoGameRepository.save(videoGame);

    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
       var videoGameDelete = videoGameRepository.findById(id);
       if (videoGameDelete.isPresent()){
           videoGameRepository.deleteById(id);
            return true;
       }
       return false;
    }
    public List<VideoGame> getVideoGameWithDiscount(Long videoGameId) {
        return videoGameRepository.findVideoGameWithDiscount(videoGameId);
    }

    @Override
    public CategoryByVideoGameDTO findCategoryByVideoGameId(Long id) {
        var category = videoGameRepository.findById(id).orElseThrow();
        var categoryDTOList = iCategoriesClient.findByVideoGameId(id);
        return CategoryByVideoGameDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .releaseDate(category.getReleaseDate())
                .price(category.getPrice())
                .categoryDTOList(categoryDTOList)
                .build();

    }

}
