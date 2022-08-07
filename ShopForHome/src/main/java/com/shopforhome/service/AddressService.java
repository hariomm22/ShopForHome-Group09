package com.shopforhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopforhome.entity.Address;
import com.shopforhome.repository.AddressRepository;



@Service
public class AddressService {

	@Autowired AddressRepository dao;
	
	public Address saveAddress(Address address) {
		return dao.save(address);
	}

	public Address findAddress(int id) {
		return dao.findById(id).get();
	}
}
