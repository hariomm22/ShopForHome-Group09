package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
