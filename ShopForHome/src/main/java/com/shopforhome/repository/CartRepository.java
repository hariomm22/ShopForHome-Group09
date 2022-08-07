package com.shopforhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Cart;
import com.shopforhome.entity.Customer;
import com.shopforhome.entity.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByCustomer(Customer cust);
	Cart findByCustomerAndProduct(Customer customer,Product product);
	void deleteByCustomer(Customer cust);
}
