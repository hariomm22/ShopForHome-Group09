package com.shopforhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Customer;
import com.shopforhome.entity.Product;
import com.shopforhome.entity.Wishlist;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findByCustomer(Customer customer);
	Wishlist findByCustomerAndProduct(Customer cust,Product pro);

}
