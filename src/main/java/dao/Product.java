package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
/*
 * define O-R mapping of product table
 */
public class Product {
	@Id //primary key
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
//	productId, productName, productLine, productScale, latitude, productVendor, productDescription
	
	@Basic
	@Column(name = "productName")
	String name;
	
	@Basic
	@Column(name = "productLine")
	String productLine;
	
	@Basic
	@Column(name = "productScale")
	String productScale;

	@Basic
	@Column(name = "productVendor")
	Integer productVendor;
	
	@Basic
	@Column(name = "productDescription")
	String productDescription;
	
	@Basic
	@Column(name = "quantityInStock")
	Integer quantityInStock;
	
	public long getId() {
		return id;
	}

	public void setId(long pk) {
		id = pk;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	
	public Integer getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(Integer productVendor) {
		this.productVendor = productVendor;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	


	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\", \"productLine\": \"%s\", "
				+ "\"productScale\": \"%s\", \"productVendor\": \"%d\", "
				+ "\"productDescription\": \"%s\", \"quantityInStock\": \"%d\"}", 
				id, name, productLine, productScale, productVendor, productDescription, quantityInStock);
	}
}