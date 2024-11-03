package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue
	private Integer pid;
	
	@Size(min=3, max=15, message="Producr Name should have only 3 to 15 character")
	@NotBlank(message="Product Name is mandatory")
	private String name;
	
	@Positive(message="Price should positive number")
	@NotNull(message="Price is mandatory")
	private double price;
	
	@Positive(message="Quantity should positive number")
	@NotNull(message="Quantity is mandatory")
	private Integer qty;
	

}
