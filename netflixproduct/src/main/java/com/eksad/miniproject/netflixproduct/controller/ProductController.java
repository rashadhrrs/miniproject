package com.eksad.miniproject.netflixproduct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired 
	ProductDao productDao;
	
	
	  @GetMapping("getAll") 
	  public List <Product> getAll() {
	  
	  
	  List<Product>result = new ArrayList<>();
	  
	  productDao.findAll().forEach(result::add);
	  
	  return result; }
	 
	
	@GetMapping("getAllBrand")
	public List <Brand> getAllBrand() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Brand>> response = restTemplate.exchange(
		  "http://localhost:8081/brand/getAll",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<Brand>>(){});
		List<Brand> brand = response.getBody();
		
		return brand;
		}
	
	
	@PostMapping(value = "save")
	public Product save(@RequestBody Product product) { 
		try {
			
			return productDao.save(product);
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(value ="update/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		
		Product productSelected = productDao.findById(id).orElse(null);
		if (productSelected!= null) {
			productSelected.setName(product.getName());
			productSelected.setPrice(product.getPrice());
			
			productDao.save(productSelected);
			return productDao.save(productSelected);
		}
		else {
			return null;
		}
		
				
	}
	
	@DeleteMapping(value ="delete/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDao.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
	}

}
