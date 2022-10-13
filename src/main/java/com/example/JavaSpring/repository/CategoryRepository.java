package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel,String> {
}
