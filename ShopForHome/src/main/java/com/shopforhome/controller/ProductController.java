package com.shopforhome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopforhome.dtos.ProductDTO;
import com.shopforhome.dtos.Response;
import com.shopforhome.entity.Product;
import com.shopforhome.helper.CsvHelper;
import com.shopforhome.service.ProductService;



@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

@Autowired ProductService bservice;
	
	@PostMapping
	public ResponseEntity<?> saveProduct(ProductDTO dto) {
		System.out.println(dto);
		Product product=ProductDTO.toEntity(dto);
		bservice.addProduct(product,dto.getPic());
		return Response.success(product);
	}
	
	@PostMapping("/bulk")
	 public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	    System.out.print("INSide Controller");
		String message = "";
	    if (CsvHelper.hasCSVFormat(file)) {
	      try {
	    	  System.out.print("INSide if Controller");
	        	bservice.saveCsv(file);
	        	System.out.print("Huibbu");
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	    }
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable("id") int id) {
		bservice.updateProduct(product);
		return Response.success(product);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findProduct(@PathVariable("id")int id) {
		Product product=bservice.findProductById(id);
		return Response.success(product);
	}
	
	@GetMapping
	public List<Product> findAllProducts(Optional<String> search) {
		List<Product> result = new ArrayList<Product>();
		if(search.isPresent()) {
			for(Product b : bservice.searchProducts(search.get())) {
				result.add(b);
			}
		}else {
			for(Product b : bservice.allProducts()) {
				result.add(b);
			}
		}
		return result;
	}
	
	
	@GetMapping("cats")
	public List<Product> findByCategory(int catid) {
		List<Product> result = new ArrayList<Product>();
		
		for(Product b : bservice.categoryProducts(catid)) {
			result.add(b);
		}
		
		return result;
	}
		
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		bservice.deleteProduct(id);
		return Response.status(HttpStatus.OK);
	}
	
}
