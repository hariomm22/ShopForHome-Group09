package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByUserid(String userid);
}
