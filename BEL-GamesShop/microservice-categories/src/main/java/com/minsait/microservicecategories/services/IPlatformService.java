package com.minsait.microservicecategories.services;

import com.minsait.microservicecategories.models.Platform;

import java.util.List;

public interface IPlatformService {
    List<Platform> findAll();
    Platform findById(Long id);
    void save(Platform platform);
    void delete(Long id);
    void update(Platform platform);
}
