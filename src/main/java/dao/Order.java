package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
/*
 * define O-R mapping of order table
 */
public class Order {
	@Id //primary key
	@Column(name = "orderId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Basic
	@Column(name = "orderDate")
	String orderDate;
	
	@Basic
	@Column(name = "requiredDate")
	String requiredDate;
	
	@Basic
	@Column(name = "shippedDate")
	String shippedDate;
	
	@Basic
	@Column(name = "status")
	String status;

	@Basic
	@Column(name = "comments")
	String comments;
	
	@Basic
	@Column(name = "customerId")
	int customerId;

	public long getId() {
		return id;
	}

	public void setId(long pk) {
		id = pk;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	public String getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"orderDate\": \"%s\", \"requiredDate\": \"%s\", "
				+ "\"shippedDate\": \"%s\", "
				+ "\"status\": \"%s\", \"comments\": \"%s\", \"customerId\": \"%d\"}", 
				id, orderDate, requiredDate, 
				shippedDate, status, comments, customerId);
	}
}