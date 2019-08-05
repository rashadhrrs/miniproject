package com.eksad.miniproject.netflixproduct.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

	
import com.eksad.miniproject.netflixproduct.model.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	
	
	

}
