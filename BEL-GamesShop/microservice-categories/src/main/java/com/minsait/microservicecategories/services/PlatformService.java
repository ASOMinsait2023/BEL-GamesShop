package com.minsait.microservicecategories.services;

import com.minsait.microservicecategories.models.Platform;
import com.minsait.microservicecategories.repositories.IPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlatformService implements IPlatformService{

    @Autowired
    private IPlatformRepository platformRepository;
    @Override
    @Transactional (readOnly = true)
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Platform findById(Long id) {
        return platformRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void save(Platform platform) {
        platformRepository.save(platform);
    }

    @Override
    @Transactional
    public void deleteId(Long id) {
        platformRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateId(Platform platform) {
        var platformToUpdate = platformRepository.findById(platform.getIdPlatform()).orElseThrow();
        platformToUpdate.setNamePlatform(platform.getNamePlatform());
        platformToUpdate.setPublisher(platform.getPublisher());
        platformToUpdate.setGeneration(platform.getGeneration());
        platformRepository.save(platform);
    }

}
