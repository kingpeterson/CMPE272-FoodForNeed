package dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
/*
 * define O-R mapping of customer table
 */
public class Customers {
	@Id //primary key
	@Column(name = "customerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
//	customerId, customerName, city, phone, latitude, longitude, addressLine1, state, country, postalCode
//	customerId, customerName, contactLastName, contactFirstName, phone, latitude, longitude, addressLine1, addressLine2, city, state, postalCode, countryb 

	@Basic
	@Column(name = "customerName")
	String name;
	
	@Basic
	@Column(name = "contactFirstname")
	String contactFirstname;
	
	@Basic
	@Column(name = "contactLastname")
	String contactLastname;
	
	@Basic
	@Column(name = "city")
	String city;
	
	@Basic
	@Column(name = "phone")
	String phone;
	
	@Basic
	@Column(name = "latitude")
	Double latitude;
	
	@Basic
	@Column(name = "longitude")
	Double longitude;
	
	@Basic
	@Column(name = "addressLine1")
	String addressLine1;
	
	@Basic
	@Column(name = "addressLine2")
	String addressLine2;
	
	@Basic
	@Column(name = "state")
	String state;
	
	@Basic
	@Column(name = "country")
	String country;
	
	@Basic
	@Column(name = "postalCode")
	String postalCode;

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
	
	public String getContactFirstname() {
		return contactFirstname;
	}

	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}
	
	public String getContactLastname() {
		return contactLastname;
	}

	public void setCantactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getAddressline1() {
		return addressLine1;
	}

	public void setAddressline1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressline2() {
		return addressLine2;
	}

	public void setAddressline2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostalcode() {
		return postalCode;
	}

	public void setPostalcode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\", \"contactFirstname\": \"%s\", \"contactLastname\": \"%s\",\"city\": \"%s\", "
				+ "\"phone\": \"%s\", \"latitude\": \"%f\", \"longitude\": \"%f\", "
				+ "\"addressLine1\": \"%s\", \"addressLine2\": \"%s\", \"state\": \"%s\", "
				+ "\"country\": \"%s\", \"postalCode\": \"%s\"}", 
				id, name, contactFirstname, contactLastname, city, phone, latitude, longitude, addressLine1, addressLine2, state, country, postalCode);
	}
}