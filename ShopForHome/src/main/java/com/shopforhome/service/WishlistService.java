package com.shopforhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopforhome.dtos.WishlistDTO;
import com.shopforhome.entity.Product;
import com.shopforhome.entity.Wishlist;
import com.shopforhome.repository.WishlistRepository;



@Service
public class WishlistService {

	@Autowired private WishlistRepository repo;
	@Autowired private CustomerService cservice;
	
	public void save(WishlistDTO dto) {
		Wishlist wl=new Wishlist();
		wl.setProduct(dto.getProduct());
		wl.setCustomer(cservice.findById(dto.getCustid()));
		repo.save(wl);
	}
	
	public List<Wishlist> findByuserid(int id){
		return repo.findByCustomer(cservice.findById(id));
	}
	
	public void deleteItem(int id) {
		repo.deleteById(id);
	}
	
	public boolean checkexist(int custid,Product product) {
		return repo.findByCustomerAndProduct(cservice.findById(custid), product)!=null;
	}
}
