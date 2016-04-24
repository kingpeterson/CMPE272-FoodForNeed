package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
/*
 * define O-R mapping of product table
 */
public class Products {
	@Id //primary key
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
//	productId, seafood, meat, vegetable, fruit, others, marketId, orderDate, srcLatitude, srcLongitude
	
	@Basic
	@Column(name = "seafood")
	Integer seafood;
	
	@Basic
	@Column(name = "meat")
	Integer meat;
	
	@Basic
	@Column(name = "vegetable")
	Integer vegetable;
	
	@Basic
	@Column(name = "fruit")
	Integer fruit;
	
	@Basic
	@Column(name = "others")
	Integer others;
	
	@Basic
	@Column(name = "marketId")
	long marketId;
	
	@Basic
	@Column(name = "orderDate")
	String orderDate;

	@Basic
	@Column(name = "srcLatitude")
	Double srcLatitude;
	
	@Basic
	@Column(name = "srcLongitude")
	Double srcLongitude;
	
	public long getId() {
		return id;
	}

	public void setId(long pk) {
		id = pk;
	}
	
	public Integer getSeafood() {
		return seafood;
	}

	public void setSeafood(Integer seafood) {
		this.seafood = seafood;
	}
	
	public Integer getMeat() {
		return meat;
	}

	public void setMeat(Integer meat) {
		this.meat = meat;
	}
	
	public Integer getVegetable() {
		return vegetable;
	}

	public void setVegetable(Integer vegetable) {
		this.vegetable = vegetable;
	}
	
	public Integer getFruit() {
		return fruit;
	}

	public void setFruit(Integer fruit) {
		this.fruit = fruit;
	}
	
	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
	}
	
	public long getMarketId() {
		return marketId;
	}

	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public Double getSrcLatitude() {
		return srcLatitude;
	}

	public void setSrcLatitude(Double srcLatitude) {
		this.srcLatitude = srcLatitude;
	}
	
	public Double getSrcLongitude() {
		return srcLongitude;
	}

	public void setSrcLongitude(Double srcLongitude) {
		this.srcLongitude = srcLongitude;
	}
	


	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"seafood\": \"%d\", \"meat\": \"%d\", "
				+ "\"vegetable\": \"%d\", \"fruit\": \"%d\", \"others\": \"%d\", "
				+ "\"marketId\": \"%d\", \"orderDate\": \"%s\", \"srcLatitude\": \"%f\", \"srcLongitude\": \"%f\"}", 
				id, seafood, meat, vegetable, fruit, others, marketId, orderDate, srcLatitude, srcLongitude);
	}
}