package com.eksad.miniproject.netflixproduct.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

	
import com.eksad.miniproject.netflixproduct.model.Product;

public interface ProductDao extends CrudRepository<Product, Long>{
	
	public Product findOneByName(String name);
	public List<Product> findByName(String name);
	public List<Product> findByPrice(BigDecimal price);
	
	

}
