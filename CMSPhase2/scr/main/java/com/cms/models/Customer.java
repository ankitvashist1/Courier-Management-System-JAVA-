package com.cms.models;

public class Customer extends User {
	
	String firstname;
	
	String lastname;
	
	String address;
	
	String email;
	
	String mobilenumber;

	


	public Customer(String username, String password, String firstname, String lastname, String address, String email,
			String mobilenumber) {
		super(username, password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.mobilenumber = mobilenumber;
	}

	public boolean addCustomer(String username, String password, String firstname, String lastname, String address, String email,
			String mobilenumber) {
		return false;
	}
	
	public Customer getCustomerDetails(String username) {
		return null;
	}
	
	public Order checkOrderStatus(String orderid) {
		return null;
	}
	


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	@Override
	public String toString() {
		return "Customer [firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", email="
				+ email + ", mobilenumber=" + mobilenumber + ", username=" + username + ", password=" + password + "]";
	}

	public Customer(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	
	public Customer() {
		super(null, null);
		// TODO Auto-generated constructor stub
	}
	

}
