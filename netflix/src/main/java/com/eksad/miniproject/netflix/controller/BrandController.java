package com.eksad.miniproject.netflix.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.miniproject.netflix.dao.BrandDao;
import com.eksad.miniproject.netflix.model.Brand;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {
	
	@Autowired 
	BrandDao brandDao;
	
	@GetMapping("getAll")
	public List <Brand> getAll() {
		
		List<Brand>result = new ArrayList<>();
		
		brandDao.findAll().forEach(result::add);
		
		return result;
	}
	
	@PostMapping(value = "save")
	public Brand save(@RequestBody Brand brand) { 
		try {
			
			return brandDao.save(brand);
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(value ="update/{id}")
	public Brand update(@RequestBody Brand brand, @PathVariable Long id) {
		
		Brand brandSelected = brandDao.findById(id).orElse(null);
		if (brandSelected!= null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());
			
			brandDao.save(brandSelected);
			return brandDao.save(brandSelected);
		}
		else {
			return null;
		}
		
				
	}
	
	@DeleteMapping(value ="delete/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDao.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
	}
	

}
