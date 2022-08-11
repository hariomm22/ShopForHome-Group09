package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("SELECT u FROM Category u WHERE u.id = ?1")
	public Category findForCsv(int id);

}
