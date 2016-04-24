package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
/*
 * define O-R mapping of order table
 */
public class Orders {
	@Id //primary key
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
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
	
	@Basic
	@Column(name = "customerId")
	long customerId;
	
	@Basic
	@Column(name = "destLatitude")
	Double destLatitude;
	
	@Basic
	@Column(name = "destLongitude")
	Double destLongitude;
	
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
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public Double getDestLatitude() {
		return destLatitude;
	}

	public void setDestLatitude(Double destLatitude) {
		this.destLatitude = destLatitude;
	}
	
	public Double getDestLongitude() {
		return destLongitude;
	}

	public void setDestLongitude(Double destLongitude) {
		this.destLongitude = destLongitude;
	}


	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"seafood\": \"%d\", \"meat\": \"%d\", "
				+ "\"vegetable\": \"%d\", \"fruit\": \"%d\", \"others\": \"%d\", "
				+ "\"marketId\": \"%d\", \"orderDate\": \"%s\", \"srcLatitude\": \"%f\", \"srcLongitude\": \"%f\""
				+ "\"customerId\": \"%d\", \"destLatitude\": \"%f\", \"destLongitude\": \"%f\"}", 
				id, seafood, meat, vegetable, fruit, others, marketId, orderDate, srcLatitude, srcLongitude, customerId, destLatitude, destLongitude);
	}
}