package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
