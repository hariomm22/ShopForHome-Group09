package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
