package com.discount.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discount.coupon.entity.Discount;
import com.discount.coupon.repository.DiscountRepository;


@Service
public class DiscountServiceImpl implements DiscountService{
	
	@Autowired 
	private DiscountRepository discountRepository;

	@Override
	public void addCoupon(Discount discount) {
		discountRepository.save(discount);
	}

	@Override
	public Discount getCoupon(Integer id) {
		return discountRepository.findById(id).orElse(null);
	}

	@Override
	public List<Discount> getAllCoupon() {
		return discountRepository.findAll();
	}

	@Override
	public Discount getCouponByCouponCode(String coupanCode) {
		return discountRepository.findByCouponCode(coupanCode);
	}

	@Override
	public void deleteCoupon(Integer id) {
		discountRepository.deleteById(id);
		
	}

}
