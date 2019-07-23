package com.eksad.miniproject.netflixproduct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "Brand")

public class Brand extends BaseEntity {
	

	
	
	@Column(nullable = false)
	private String name;
	
	@Column(name= "product_type")
	private String productType;

}
