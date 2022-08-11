package com.discount.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discount.coupon.entity.Discount;
import com.discount.coupon.service.DiscountService;

@CrossOrigin
@RestController
@RequestMapping("/api/discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@PostMapping
	public ResponseEntity<?> addCoupon(@RequestBody Discount discount){
		try {
			 discountService.addCoupon(discount);
			 return ResponseEntity.status(HttpStatus.OK).body("{\"Coupon added Successfully \"}");
		}catch(Exception e) {
			System.out.print("Hariom");
			e.printStackTrace();
		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not add Coupon");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCoupon(@PathVariable Integer id){
		try {
			 
			 return ResponseEntity.status(HttpStatus.OK).body(discountService.getCoupon(id));
		}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not get Coupon");
		}
	}
	
	@GetMapping("coupon/{couponCode}")
	public ResponseEntity<?> getCouponByCouponCode(@PathVariable String couponCode){
		try {
			 
			 return ResponseEntity.status(HttpStatus.OK).body(discountService.getCouponByCouponCode(couponCode));
		}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not get Coupon");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCoupon(){
		try {
			 
			 return ResponseEntity.status(HttpStatus.OK).body(discountService.getAllCoupon());
		}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not get Coupon");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable Integer id){
		try {
			discountService.deleteCoupon(id);
			 return ResponseEntity.status(HttpStatus.OK).body("Coupon deleted");
		}catch(Exception e) {
		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not delete Coupon");
		}
	}
	
	
}
