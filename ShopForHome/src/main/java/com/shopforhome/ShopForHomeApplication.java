package com.shopforhome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.shopforhome.entity.Admin;
import com.shopforhome.service.AdminService;


@SpringBootApplication
public class ShopForHomeApplication {

	private static final Logger log = LoggerFactory.getLogger(ShopForHomeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShopForHomeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(AdminService srv) {
	    return (args) -> {
	    	if(srv.count()==0) {
	    		srv.updateAdmin(new Admin("admin", "admin", "Administrator"));
	    		log.info("Admin user created successfully");
	    	}
	    };
	}

}
