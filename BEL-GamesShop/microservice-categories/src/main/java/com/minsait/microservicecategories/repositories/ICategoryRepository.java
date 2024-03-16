package com.minsait.microservicecategories.repositories;

import com.minsait.microservicecategories.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Categories, Long>{
}
