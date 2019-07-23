package com.eksad.miniproject.netflixproduct.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eksad.miniproject.netflixproduct.dao.ProductDao;
import com.eksad.miniproject.netflixproduct.model.Brand;
import com.eksad.miniproject.netflixproduct.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Service
@RestController
@RequestMapping(value = "/product")
@Api(tags = "Product")
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@ApiOperation(
			value= "API to get all Product",
			notes= "Return data with JSON Format ",
			tags= "Get Data API"
			)

	@GetMapping("getAll")
	public List<Product> getAll() {

		List<Product> result = new ArrayList<>();

		productDao.findAll().forEach(result::add);

		return result;
	}

	//@GetMapping("getAllBrand")
	
	/*
	 * public List<Brand> getAllBrand() { RestTemplate restTemplate = new
	 * RestTemplate(); ResponseEntity<List<Brand>> response =
	 * restTemplate.exchange("http://localhost:8081/brand/getAll", HttpMethod.GET,
	 * null, new ParameterizedTypeReference<List<Brand>>() { }); List<Brand> brand =
	 * response.getBody();
	 * 
	 * return brand; }
	 */
	 
	
	
	@GetMapping("getAllBrand")
	  @HystrixCommand(fallbackMethod = "fallbackList" )
	
	 public String readingList() { 
		
	  RestTemplate restTemplate = new RestTemplate();
		
		  ResponseEntity<List<Brand>> response = restTemplate.exchange(
		  "http://localhost:8081/brand/getAll", HttpMethod.GET, null, new
		  ParameterizedTypeReference<List<Brand>>(){}); List<Brand> brand =
		  response.getBody();
		 
		
		
	  
	  return response.toString();
	  
	  }
	  
	  
	  
	  //@SuppressWarnings("unused")
	
	public String fallbackList() {
	  
	  //System.out.println("Service is down!!! fallback route enabled...");
	  
	  return "Circuit breaker enabled"; }
	 
	 
	

	  @ApiOperation(
				value= "API to save Product",
				notes= "Return data with JSON Format ",
				tags= "Data Manipulation API"
				)	
	@PostMapping(value = "save")
	public Product save(@RequestBody Product product) {
		try {

			return productDao.save(product);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	  
	  @ApiOperation(
				value= "API to update Product",
				notes= "Return data with JSON Format ",
				tags= "Data Manipulation API"
				)

	@PutMapping(value = "update/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id) {

		Product productSelected = productDao.findById(id).orElse(null);
		if (productSelected != null) {
			productSelected.setName(product.getName());
			productSelected.setPrice(product.getPrice());

			productDao.save(productSelected);
			return productDao.save(productSelected);
		} else {
			return null;
		}

	}
	  
	  @ApiOperation(
				value= "API to delete Product",
				notes= "Return data with JSON Format ",
				tags= "Data Manipulation API"
				)

	@DeleteMapping(value = "delete/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
	}

}
