package com.shopforhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopforhome.entity.Category;
import com.shopforhome.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired private CategoryRepository repo;
	
	public void save(Category cat) {
		repo.save(cat);
	}
	
	public List<Category> listAll(){
		return repo.findAll();
	}
	
	public Category findById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public Category findForCsv(int id) {
		return repo.findForCsv(id);
	}
	
	public void deleteCategory(int id) {
		repo.deleteById(id);
	}
}
