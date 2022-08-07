package com.shopforhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
