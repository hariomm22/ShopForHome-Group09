package com.shopforhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Order;
import com.shopforhome.entity.OrderDetails;



@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	List<OrderDetails> findByOrder(Order order);
}
