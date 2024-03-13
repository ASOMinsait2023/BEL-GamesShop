package com.minsait.microservicecategories.repositories;

import com.minsait.microservicecategories.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlatformRepository extends JpaRepository<Platform, Long> {
}
