package com.discount.coupon.service;

import java.util.List;

import com.discount.coupon.entity.Discount;

public interface DiscountService {
	public void addCoupon(Discount discount);
	
	public Discount getCoupon(Integer id);
	
	public List<Discount> getAllCoupon();
	
	public Discount getCouponByCouponCode(String coupanCode);
	
	public void deleteCoupon(Integer id);
}
