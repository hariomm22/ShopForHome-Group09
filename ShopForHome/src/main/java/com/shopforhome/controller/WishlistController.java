package com.shopforhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopforhome.dtos.Response;
import com.shopforhome.dtos.WishlistDTO;
import com.shopforhome.entity.Wishlist;
import com.shopforhome.service.WishlistService;



@CrossOrigin
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired private WishlistService wservice;
	
	@PostMapping
	public ResponseEntity<?> saveItem(@RequestBody WishlistDTO wl) {
		System.out.println(wl);
		if(wservice.checkexist(wl.getCustid(), wl.getProduct())) {
			return ResponseEntity.badRequest().body("Item already exists");
		}
		wservice.save(wl);
		return Response.success("Item saved into Wishlist");
	}
	
	@GetMapping
	public List<Wishlist> listall(int custid){
		return wservice.findByuserid(custid);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") int id){
		wservice.deleteItem(id);
		return Response.success("item deleted successfully");
	}
}
