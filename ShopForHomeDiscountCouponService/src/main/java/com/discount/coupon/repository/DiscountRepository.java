package com.discount.coupon.repository;

import com.discount.coupon.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
	
	public Discount findByCouponCode(String couponCode);

}
