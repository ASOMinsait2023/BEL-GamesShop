package com.minsait.repositories;

import com.minsait.models.VideoGame;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IVideoGameRespository extends CrudRepository<VideoGame,Long> {
}
