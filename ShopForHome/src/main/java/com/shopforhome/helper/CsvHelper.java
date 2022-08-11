package com.shopforhome.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.shopforhome.entity.Category;
import com.shopforhome.entity.Product;
import com.shopforhome.service.CategoryService;



public class CsvHelper {
	
	
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "pname", "descr", "cat_id","price","photo"};
  

  
  public static boolean hasCSVFormat(MultipartFile file) {
    if (!TYPE.equals(file.getContentType())) {
      return false;
    }
    return true;
  }
  public static List<Product> csvToProducts(InputStream is) { 
	CategoryService categoryService = new CategoryService();
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
      List<Product> products = new ArrayList<Product>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
    	  Product product = new Product();
    	  product.setPname(csvRecord.get("pname"));
    	  product.setDescr(csvRecord.get("descr")); 
//    	  Integer id=Integer.parseInt(csvRecord.get("cat_id"));
//    	  System.out.println("cat_id Is "+id);
//    	  Category categoryObj = categoryService.findForCsv(id); 
//    	  product.setCategory(categoryObj);
    	  product.setPrice(Integer.parseInt(csvRecord.get("price")));
    	  product.setPhoto(csvRecord.get("photo"));
    	  products.add(product);
    	  System.out.println("inside cstp");
    	  System.out.println(product);
      }
      return products;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}