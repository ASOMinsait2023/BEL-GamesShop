package com.minsait.microservicecategories.services;

import com.minsait.microservicecategories.models.Platform;

import java.util.List;

public interface IPlatformService {
    List<Platform> findAll();
    Platform findById(Long id);
    void save(Platform platform);
    void deleteId(Long id);
    void updateId(Platform platform);
}
