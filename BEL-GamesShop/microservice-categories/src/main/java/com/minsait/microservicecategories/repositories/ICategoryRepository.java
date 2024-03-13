package com.minsait.microservicecategories.repositories;

import com.minsait.microservicecategories.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Categories, Long>{
}
