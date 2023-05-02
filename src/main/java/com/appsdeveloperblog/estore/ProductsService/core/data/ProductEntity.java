package com.appsdeveloperblog.estore.ProductsService.core.data;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="products")
@Data
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227264951080660124L;
	
	@Id
	@Column(unique=true)
	private String productId;
	
	@Column(unique=true)
	private String title;
	private BigDecimal price;
	private Integer quantity;

}
