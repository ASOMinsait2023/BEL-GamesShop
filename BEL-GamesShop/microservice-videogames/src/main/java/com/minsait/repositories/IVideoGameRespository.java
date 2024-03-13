package com.minsait.repositories;

import com.minsait.models.VideoGame;
import org.springframework.data.repository.CrudRepository;

public interface IVideoGameRespository extends CrudRepository<VideoGame,Long> {
}
